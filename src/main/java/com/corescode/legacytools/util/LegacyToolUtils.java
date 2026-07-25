package com.corescode.legacytools.util;

import com.corescode.legacytools.item.ModItems;
import com.corescode.legacytools.legacy.LegacyToolType;
import net.minecraft.world.item.ItemStack;

public final class LegacyToolUtils {

    private LegacyToolUtils() {
    }

    public static boolean isLegacyPickaxe(ItemStack stack) {
        return stack.is(ModItems.RUSTED_PICKAXE)
                || stack.is(ModItems.WORN_PICKAXE)
                || stack.is(ModItems.RESTORED_PICKAXE)
                || stack.is(ModItems.PERFECTED_PICKAXE);
    }

    public static LegacyToolType getToolType(ItemStack stack) {

        if (isLegacyPickaxe(stack)) {
            return LegacyToolType.PICKAXE;
        }

        return null;
    }
}