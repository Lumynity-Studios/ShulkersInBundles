import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import me.modmuss50.mpp.ModPublishExtension
import me.modmuss50.mpp.ReleaseType
import java.util.Locale
import java.util.Locale.getDefault

plugins {
    java
    `maven-publish`
    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.mod.publish) apply false
    id("idea")
}

val mcVersion = libs.versions.minecraft.get()

allprojects {
    apply(plugin = "java")
    apply(plugin = "idea")

    version = "${rootProject.property("mod_version")}+mc${mcVersion}"
    group = rootProject.property("maven_group") as String

    base {
        archivesName.set(rootProject.property("archives_base_name") as String)
    }

    repositories {
        // All repositories used should be listed here
        mavenCentral()
        maven("https://maven.fabricmc.net")
        maven("https://maven.neoforged.net/releases")
        maven("https://maven.minecraftforge.net")
    }

    val targetJavaVersion = 25

    tasks {
        withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
                options.release.set(targetJavaVersion)
            }
        }

        processResources {
            filteringCharset = "UTF-8"
        }

        jar {
            from("LICENSE") {
                rename {
                    "$it"
                }
            }
        }
    }

    java {
        val javaVersion = JavaVersion.toVersion(targetJavaVersion)
        if (JavaVersion.current() < javaVersion) {
            toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
        }

        withSourcesJar()
    }

    // IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
    idea {
        module {
            isDownloadSources = true
            isDownloadJavadoc = true
        }
    }
}

subprojects {
    apply(plugin = "com.gradleup.shadow")
    apply(plugin = "maven-publish")

    val shadowCommon by configurations.creating

    tasks {
        jar {
            archiveClassifier.set("slim")
        }

        named<ShadowJar>("shadowJar") {
            configurations = listOf(shadowCommon)
            archiveClassifier.set(null)
        }
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifactId = base.archivesName.get()
                from(components["java"])
            }
        }

        // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
        repositories {
            /*maven("https://mvn.devos.one/releases") {
                name = "devOS"
                credentials {
                    username = System.getenv()["MAVEN_USER"]
                    password = System.getenv()["MAVEN_PASS"]
                }
            }*/
        }
    }

    if (project.name != "common") {
        apply(plugin = "me.modmuss50.mod-publish-plugin")

        tasks.publish {
            finalizedBy("publishMods")
        }

        extensions.configure<ModPublishExtension>("publishMods") {
            displayName = "${project.version} (${if (project.name == "neoforge") "NeoForge" else project.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    getDefault()
                ) else it.toString()
            }
            })"
            version = "${project.version}-${project.name}"
            changelog = rootProject.file("CHANGELOG.md").readText()
            type = ReleaseType.STABLE
            modLoaders.add(this.project.name)
            file.set(tasks.named<ShadowJar>("shadowJar").get().archiveFile.get())

            dryRun = providers.environmentVariable("MODRINTH_TOKEN").orNull == null || providers.environmentVariable("CURSEFORGE_TOKEN").orNull == null

            modrinth {
                projectId = rootProject.property("publish.modrinth") as String
                accessToken = providers.environmentVariable("MODRINTH_TOKEN")

                minecraftVersions.addAll((rootProject.property("supported_versions") as String).split(",").map { it.trim() })
            }

            curseforge {
                projectId = rootProject.property("publish.curseforge") as String
                accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")

                minecraftVersions.addAll((rootProject.property("supported_versions") as String).split(",").map { it.trim() })
            }
        }
    }
}
