package net.justmili.shulkersinbundles.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.shulkersinbundles.core.util.ItemWeights;
import net.justmili.shulkersinbundles.core.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BundleContents.class)
public class BundleContentsMixin {

    // Modify weight of Shulker Boxes to be a different fraction
    @ModifyReturnValue(method = "getWeight", at = @At("RETURN"))
    private static Fraction sib$modifyShulkerWeight(Fraction original, ItemStack stack) {
        return Util.isShulkerBox(stack.getItem())? ItemWeights.getShulker() : original;
    }
}