package com.corescode.legacytools.util;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.item.ModItems;
import com.corescode.legacytools.legacy.LegacyToolType;
import net.minecraft.world.item.ItemStack;

public final class LegacyUpgradeUtils {

    private LegacyUpgradeUtils() {
    }

    public static ItemStack upgrade(ItemStack stack) {

        LegacyToolType toolType = LegacyToolUtils.getToolType(stack);

        return switch (toolType) {
            case PICKAXE -> upgradePickaxe(stack);
            case AXE -> throw new UnsupportedOperationException("Axe upgrades are not implemented yet.");
            case SHOVEL -> throw new UnsupportedOperationException("Shovel upgrades are not implemented yet.");
        };
    }

    private static ItemStack upgradePickaxe(ItemStack oldStack) {

        LegacyToolData data = oldStack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        ItemStack newStack = switch (data.stage()) {

            case RUSTED -> oldStack.transmuteCopy(ModItems.WORN_PICKAXE);

            case WORN -> oldStack.transmuteCopy(ModItems.RESTORED_PICKAXE);

            case RESTORED -> oldStack.transmuteCopy(ModItems.PERFECTED_PICKAXE);

            case PERFECTED -> oldStack.copy();
        };

        LegacyStage nextStage = switch (data.stage()) {

            case RUSTED -> LegacyStage.WORN;

            case WORN -> LegacyStage.RESTORED;

            case RESTORED -> LegacyStage.PERFECTED;

            case PERFECTED -> LegacyStage.PERFECTED;
        };

        newStack.set(
                ModDataComponents.LEGACY_DATA,
                new LegacyToolData(
                        nextStage,
                        0,
                        data.lastUsedGameTime(),
                        data.cooldownEndGameTime()
                )
        );

        return newStack;
    }

}