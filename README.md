![Titlecard](https://cdn.modrinth.com/data/cached_images/d5f8f514be0d437dabd2bc846e5b3d6125341fd5.png)

<pre><sub>"Cool, more storage options"</sub></pre>

<hr>

***GitHub Notice: This is a `Repository Information` branch. If you wish to see the code, please change to a different branch.***

<hr>

## Description
Shulkers In Bundles is a small but **multi-loader and always up-to-date** QoL mod that makes bundles just a tiny bit more useful late-game
by allowing to put up to 16 shulker boxes in a single bundle.
<br>
To prevent infinite storage in a single slot, using the mod to e.g. chunk-ban people, or even just overloading servers or 
your own singleplayer worlds with too much data, the mod adds, disables and modifies some things.
- You can put (by default) up to 16 shulker boxes in a bundle
- You can still put a bundle in a bundle, but:
  - The bundle you're trying to insert can not contain a bundle or a shulker box
- You can't put bundles or shulker boxes into bundles that are already in a shulker box
- Any means of item transportation (hoppers etc.) can not input bundles with a shulker box or another bundle inside it into a shulker box

Additional changes for 1.21.1 port:
- Backported Bundle recipe
- Bundles are always enabled, even if you haven't turned on experimental features before

<hr>

### Open API
For a little bit of customization without us struggling to create configs for each loader for so many versions, 
Shulkers In Bundles exposes a supported API which you can easily add into another mod to change how much space a shulker box takes up inside a bundle.<br>
The default weight is `1/16`, meaning you can fit up to 16 shulker boxes in a Bundle.

<details>
<summary>Example (Fabric)</summary>
This example only applies to Shulkers In Bundles 1.2.2+

```java
// In your mod initializer class;
package net.example.mod;

import org.apache.commons.lang3.math.Fraction;
// Import the ItemWeights class from Shulkers In Bundles
import net.justmili.shulkersinbundles.core.util.ItemWeights;
import net.fabricmc.api.ModInitializer;

public final class ExampleMod implements ModInitializer {

    @Override
    public void onInitialize() {

        // You can set what fraction of the bundle's storage a shulker box takes up
        // For example, 1/8th means each shulker box takes up 1/8th of the bundle,
        // allowing up to 8 shulker boxes in a Bundle
        ItemWeights.setShulker(Fraction.getFraction(1, 8));

        // Both the numerator and denominator are clamped between 1 and 64
        // For example, this would be clamped to 1/64:
        // ItemWeights.setShulker(Fraction.getFraction(1, 69));

        // Or you can just set the denominator
        // This will automatically use 1 as the numerator
        // For example, this allows up to 24 shulker boxes in a Bundle
        ItemWeights.setShulker(24);
    }
}
```

</details>

<hr>

<details>
<summary>FAQ: Can it work server-side without the client?</summary>
It can. The client does not need the mod although the bundle tooltip will look a little cursed without it.
</details>
<details>
<summary>FAQ: How long will it be kept updated?</summary>
As long as the maintainer lives, probably.
</details>

<hr>

Having issues, need to report a bug or just want to chat? Join our [official community Discord server](https://discord.gg/BcCDgnsCwQ)!
