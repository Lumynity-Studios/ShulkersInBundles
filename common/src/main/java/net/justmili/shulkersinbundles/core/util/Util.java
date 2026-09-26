package net.justmili.shulkersinbundles.core.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class Util {

    public static boolean isBundleWithAllowed(ItemStack stack) {
        var contents = stack.get(DataComponents.BUNDLE_CONTENTS);

        if (contents == null || contents.isEmpty()) return true;
        // No item tags to check is it a bundle or a shulker box, wasn't added yet
        for (var item : contents.items()) {
            if (isShulkerOrBundle(item.getItem())) return false;
        }
        return true;
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