package net.justmili.shulkersinbundles.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.DataResult;
import net.justmili.shulkersinbundles.core.util.ItemWeights;
import net.justmili.shulkersinbundles.core.util.Util;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BundleContents.class)
public class BundleContentsMixin {

    // Allow Shulker Boxes to be put inside bundles
    @ModifyReturnValue(method = "canItemBeInBundle", at = @At("RETURN"))
    private static boolean sib$allowShulkersInBundles(boolean original, ItemStack stack) {
        if (stack.isEmpty()) return original;
        return Util.isShulkerBox(stack.getItem()) || original;
    }

    // Modify weight of Shulker Boxes to be a different fraction
    @ModifyReturnValue(method = "getWeight", at = @At("RETURN"))
    private static DataResult<Fraction> sib$modifyShulkerWeight(DataResult<Fraction> original, ItemInstance instance) {
        return Util.isShulkerBox(instance.typeHolder().value())? DataResult.success(ItemWeights.getShulker()) : original;
    }

    // Prevent Bundles containing items in Bundles
    @ModifyReturnValue(method = "canItemBeInBundle", at = @At("RETURN"))
    private static boolean sib$preventBundlesInBundles(boolean original, ItemStack stack) {
        return Util.preventInteract(stack, original);
    }
}
