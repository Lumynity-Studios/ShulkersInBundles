package net.justmili.shulkersinbundles.core.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.justmili.shulkersinbundles.core.util.Util;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BundleItem.class)
public abstract class BundleItemMixin {

    // Prevent inserting Shulker Boxes and Bundles into Bundles that are already in a Shulker Box
    @Definition(id = "allowModification", method = "Lnet/minecraft/world/inventory/Slot;allowModification(Lnet/minecraft/world/entity/player/Player;)Z")
    @Expression("?.allowModification(?)")
    @ModifyExpressionValue(method = "overrideOtherStackedOnMe", at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 0))
    private boolean sib$preventModification(boolean original, ItemStack self, ItemStack other, Slot slot, ClickAction click, Player player, SlotAccess carriedItem) {
        return !(slot instanceof ShulkerBoxSlot && Util.isShulkerOrBundle(other.getItem())) && original;
    }
}