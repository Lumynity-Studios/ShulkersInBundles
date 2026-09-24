package net.justmili.shulkersinbundles.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.shulkersinbundles.core.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShulkerBoxBlockEntity.class)
public class ShulkerBoxBlockEntityMixin {

    // Prevent Bundles containing items going into Shulkers via item transportation
    @ModifyReturnValue(method = "canPlaceItemThroughFace", at = @At("RETURN"))
    private boolean sib$preventBundlesInShulkers(boolean original, int slot, ItemStack stack) {
        return Util.preventInteract(stack, original);
    }
}
