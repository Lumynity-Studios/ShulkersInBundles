package net.justmili.shulkersinbundles.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.shulkersinbundles.core.util.Util;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleContents.Mutable.class)
public class BundleContentsMutableMixin {

    // Allow Shulker Boxes to be put inside bundles
    @WrapOperation(method = "tryInsert", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;canFitInsideContainerItems()Z"), require = 0)
    private boolean sib$allowShulkersInBundles(Item item, Operation<Boolean> original) {
        return Util.isShulkerBox(item) || original.call(item);
    }

    // Prevent Bundles containing Shulker Boxes or Bundles from being put inside other Bundles (direct insert / click-stack)
    @Inject(method = "tryInsert", at = @At("HEAD"), cancellable = true)
    private void sib$preventBundlesInBundles(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (!Util.isBundleWithAllowed(stack)) cir.setReturnValue(0);
    }

    // Prevent Bundles containing Shulker Boxes or Bundles from being put inside other Bundles (shift-click transfer)
    @Inject(method = "tryTransfer", at = @At("HEAD"), cancellable = true)
    private void sib$preventBundlesInBundlesFromSlot(Slot slot, Player player, CallbackInfoReturnable<Integer> cir) {
        if (!Util.isBundleWithAllowed(slot.getItem())) cir.setReturnValue(0);
    }
}