package net.justmili.shulkersinbundles.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.shulkersinbundles.core.util.Util;
import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShulkerBoxSlot.class)
public class ShulkerBoxSlotMixin {

    // Prevent Bundles containing items from being placed into Shulkers
    @ModifyReturnValue(method = "mayPlace", at = @At("RETURN"))
    private boolean sib$preventBundlesInShulkers(boolean original, ItemStack stack) {
        return Util.preventInteract(stack, original);
    }
}