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
            case AXE -> upgradeAxe(stack);
            case SHOVEL -> upgradeShovel(stack);
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
                        data.lastInteractionGameTime(),
                        data.abilityStartGameTime(),
                        data.cooldownEndGameTime()
                )
        );

        return newStack;
    }

    private static ItemStack upgradeAxe(ItemStack oldStack) {

        LegacyToolData data = oldStack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        ItemStack newStack = switch (data.stage()) {
            case RUSTED -> oldStack.transmuteCopy(ModItems.WORN_AXE);
            case WORN -> oldStack.transmuteCopy(ModItems.RESTORED_AXE);
            case RESTORED -> oldStack.transmuteCopy(ModItems.PERFECTED_AXE);
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
                        data.lastInteractionGameTime(),
                        data.abilityStartGameTime(),
                        data.cooldownEndGameTime()
                )
        );

        return newStack;
    }

    private static ItemStack upgradeShovel(ItemStack oldStack) {

        LegacyToolData data = oldStack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        ItemStack newStack = switch (data.stage()) {
            case RUSTED -> oldStack.transmuteCopy(ModItems.WORN_SHOVEL);
            case WORN -> oldStack.transmuteCopy(ModItems.RESTORED_SHOVEL);
            case RESTORED -> oldStack.transmuteCopy(ModItems.PERFECTED_SHOVEL);
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
                        data.lastInteractionGameTime(),
                        data.abilityStartGameTime(),
                        data.cooldownEndGameTime()
                )
        );

        return newStack;
    }
}