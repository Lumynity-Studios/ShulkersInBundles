package net.justmili.shulkersinbundles.core.mixin.neoforge;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.shulkersinbundles.core.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BundleContents.Mutable.class)
public class BundleContentsMutableMixin {

    // Allow Shulker Boxes to be put inside bundles
    // NeoForged, why
    @WrapOperation(method = "tryInsert", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;canFitInsideContainerItems()Z"), require = 0)
    private boolean sib$allowShulkersInBundles(ItemStack stack, Operation<Boolean> original) {
        return Util.isShulkerBox(stack.getItem()) || original.call(stack);
    }
}