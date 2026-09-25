package net.justmili.shulkersinbundles.core.mixin;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FeatureFlags.class)
public abstract class FeatureFlagsMixin {

    @Mutable
    @Shadow
    @Final
    public static FeatureFlagSet DEFAULT_FLAGS;

    // Always enable Bundles
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void sib$alwaysEnableBundle(CallbackInfo ci) {
        DEFAULT_FLAGS = DEFAULT_FLAGS.join(FeatureFlagSet.of(FeatureFlags.BUNDLE));
    }
}