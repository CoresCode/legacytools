package com.corescode.legacytools.util;

import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.legacy.LegacyToolType;
import net.minecraft.world.item.ItemStack;

public final class LegacyUpgradeUtils {

    private LegacyUpgradeUtils() {
    }

    public static ItemStack upgrade(ItemStack stack) {

        LegacyToolType toolType = LegacyToolUtils.getToolType(stack);

        return switch (toolType) {
            case PICKAXE -> upgradePickaxe(stack);
            case AXE -> upgradeAxe(stack);
            case SHOVEL -> upgradeShovel(stack);
        };
    }

    private static ItemStack upgradePickaxe(ItemStack oldStack) {

        LegacyToolData data = oldStack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        return LegacyTransformationUtils.transform(
                oldStack,
                data.stage().next()
        );
    }

    private static ItemStack upgradeAxe(ItemStack oldStack) {

        LegacyToolData data = oldStack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        return LegacyTransformationUtils.transform(
                oldStack,
                data.stage().next()
        );
    }

    private static ItemStack upgradeShovel(ItemStack oldStack) {

        LegacyToolData data = oldStack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        return LegacyTransformationUtils.transform(
                oldStack,
                data.stage().next()
        );
    }
}