package com.corescode.legacytools.util;

import com.corescode.legacytools.item.ModItems;
import com.corescode.legacytools.legacy.LegacyToolType;
import net.minecraft.world.item.ItemStack;

public final class LegacyToolUtils {

    private LegacyToolUtils() {
    }

    public static boolean isLegacyPickaxe(ItemStack stack) {
        return getToolType(stack) == LegacyToolType.PICKAXE;
    }

    public static boolean isLegacyAxe(ItemStack stack) {
        return getToolType(stack) == LegacyToolType.AXE;
    }

    public static boolean isLegacyShovel(ItemStack stack) {
        return getToolType(stack) == LegacyToolType.SHOVEL;
    }

    public static LegacyToolType getToolType(ItemStack stack) {

        if (stack.is(
                ModItems.RUSTED_PICKAXE
        ) || stack.is(
                ModItems.WORN_PICKAXE
        ) || stack.is(
                ModItems.RESTORED_PICKAXE
        ) || stack.is(
                ModItems.PERFECTED_PICKAXE
        )) {
            return LegacyToolType.PICKAXE;
        }

        if (stack.is(
                ModItems.RUSTED_AXE
        ) || stack.is(
                ModItems.WORN_AXE
        ) || stack.is(
                ModItems.RESTORED_AXE
        ) || stack.is(
                ModItems.PERFECTED_AXE
        )) {
            return LegacyToolType.AXE;
        }

        if (stack.is(
                ModItems.RUSTED_SHOVEL
        ) || stack.is(
                ModItems.WORN_SHOVEL
        ) || stack.is(
                ModItems.RESTORED_SHOVEL
        ) || stack.is(
                ModItems.PERFECTED_SHOVEL
        )) {
            return LegacyToolType.SHOVEL;
        }

        return null;
    }
}