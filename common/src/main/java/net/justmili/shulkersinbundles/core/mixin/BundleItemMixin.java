package net.justmili.shulkersinbundles.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BundleItem.class)
public abstract class BundleItemMixin {

    @Shadow
    private static void playInsertFailSound(Entity entity) {
    }

    // TODO: Fix where you can insert one item into a bundle that is in a shulker and then nothing else more (prevent insert entirely)
    // Prevent inserting items into empty Bundles that are already in a Shulker Box
    @ModifyReturnValue(method = "overrideOtherStackedOnMe", at = @At("RETURN"))
    private boolean sib$preventInsertIntoBundleInShulker(boolean original, ItemStack self, ItemStack other, Slot slot, ClickAction click, Player player, SlotAccess carriedItem) {
        if (!(click == ClickAction.PRIMARY && !other.isEmpty())) return original;
        if (slot instanceof ShulkerBoxSlot) {
            playInsertFailSound(player);
            return true;
        }
        return original;
    }
}