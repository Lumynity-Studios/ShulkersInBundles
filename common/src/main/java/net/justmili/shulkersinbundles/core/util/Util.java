package net.justmili.shulkersinbundles.core.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class Util {

    public static boolean isBundleAndEmpty(ItemStack stack) {
        return stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).equals(BundleContents.EMPTY);
    }

    public static boolean preventInteract(ItemStack stack, boolean original) {
        return Util.isBundleAndEmpty(stack) && original;
    }

    public static boolean allowInteract(ItemStack stack, boolean original) {
        return Util.isBundleAndEmpty(stack) || original;
    }

    public static boolean isShulkerBox(Item item) {
        return item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock;
    }
}
