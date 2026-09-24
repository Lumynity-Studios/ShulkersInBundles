![Titlecard](https://cdn.modrinth.com/data/cached_images/d5f8f514be0d437dabd2bc846e5b3d6125341fd5.png)

<div style="text-align: center;">"Cool, more storage options"</div>

***

## Description

Shulkers In Bundles is a small but **multi-loader and always up-to-date** QoL mod that makes bundles just a tiny bit more useful late-game by allowing to put up to 16 shulkers in a single bundle.

### Drawbacks

To prevent infinite storage in a single slot, using the mod to perhaps chunk-ban people, or even just overloading servers or your own singleplayer worlds, the mod disables some things.

- You can no longer put Bundles in Shulkers
  - Except if they're empty
- You can no longer put Bundles in Bundles
  - Except if they're empty

***

### Open API

For a little bit of customization without us struggling to create configs for each loader for so many versions, Shulkers In Bundles exposes a supported API which you can mixin into and change how many shulkers you want to be able to put in a single bundle.

**_Mixin Code Example pre-1.2.2;_**

<div class="spoiler"><pre><code>package xyz.yourself.mod.mixin; //doesn't matter what your package is named

import net.justmili.shulkersinbundles.data.ShulkerFractions;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerFractions.class)
public class ShulkerWeightMixin {
    @Inject(method = "getShulkerWeight", at = @At("HEAD"), cancellable = true)
    private static void modifyShulkerWeight(CallbackInfoReturnable&lt;Fraction&gt; cir) {
        cir.setReturnValue(Fraction.getFraction(1, 8));
        // ^^^ The 2nd number is how many shulkers will fit.
        // (To be exact, this for example is 1/8th of the bundle per shulker, so 8 shulkers will fit.)
        // Minimum is 1, maximum is 64.
    }
}

</code></pre></div>

**_Mixin Code Example 1.2.2+;_**

<div class="spoiler">
  Just use pre-1.2.2 but change
  <pre><code>import net.justmili.shulkersinbundles.data.ShulkerFractions;</code></pre>
  to 
  <pre><code>import net.justmili.shulkersinbundles.core.util.ShulkerWeight;</code></pre>
</div>

Keep in mind, if multiple mods modify the shulker weight, the last-applied (or with highest priority) mixin will be used.

***

FAQ: Can it work server-side without the client?

<div class="spoiler">It can. The client does not need the mod although the bundle tooltip will look a little cursed without it.</div>

FAQ: How long will it be kept updated?

<div class="spoiler">Quote from Millie, team lead and maintainer of this project: "Until I'm 2 meters under"</div>

***

Having issues, need to report a bug or just want to chat? Join our [official community Discord server](https://discord.gg/BcCDgnsCwQ)!
