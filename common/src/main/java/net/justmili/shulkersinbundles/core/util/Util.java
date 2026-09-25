package net.justmili.shulkersinbundles.core.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class Util {

    public static boolean isBundleWithAllowed(ItemStack stack) {
        var contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        boolean allowInteract = true;

        for (var item : contents.items()) {

            // No item tags to check is it a bundle or a shulker box, wasn't added yet
            if (isShulkerOrBundle(item.getItem())) {
                allowInteract = false;
                break;
            }
        }
        return contents.isEmpty() || allowInteract;
    }

    public static boolean preventInteract(ItemStack stack, boolean original) {
        return Util.isBundleWithAllowed(stack) && original;
    }

    public static boolean allowInteract(ItemStack stack, boolean original) {
        return Util.isBundleWithAllowed(stack) || original;
    }

    public static boolean isShulkerOrBundle(Item item) {
        return isShulkerBox(item) || item instanceof BundleItem;
    }

    public static boolean isShulkerBox(Item item) {
        return item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock;
    }
}