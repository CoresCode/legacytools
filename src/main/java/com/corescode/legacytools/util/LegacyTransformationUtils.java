package com.corescode.legacytools.util;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.item.ModItems;
import com.corescode.legacytools.legacy.LegacyToolType;
import net.minecraft.world.item.ItemStack;

public final class LegacyTransformationUtils {

    private LegacyTransformationUtils() {
    }

    public static ItemStack transform(ItemStack oldStack, LegacyStage targetStage) {

        LegacyToolType toolType = LegacyToolUtils.getToolType(oldStack);

        if (toolType == null) {
            return oldStack.copy();
        }

        LegacyToolData data = oldStack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        ItemStack newStack = switch (toolType) {

            case PICKAXE -> switch (targetStage) {
                case RUSTED -> oldStack.transmuteCopy(ModItems.RUSTED_PICKAXE);
                case WORN -> oldStack.transmuteCopy(ModItems.WORN_PICKAXE);
                case RESTORED -> oldStack.transmuteCopy(ModItems.RESTORED_PICKAXE);
                case PERFECTED -> oldStack.transmuteCopy(ModItems.PERFECTED_PICKAXE);
            };

            case AXE -> switch (targetStage) {
                case RUSTED -> oldStack.transmuteCopy(ModItems.RUSTED_AXE);
                case WORN -> oldStack.transmuteCopy(ModItems.WORN_AXE);
                case RESTORED -> oldStack.transmuteCopy(ModItems.RESTORED_AXE);
                case PERFECTED -> oldStack.transmuteCopy(ModItems.PERFECTED_AXE);
            };

            case SHOVEL -> switch (targetStage) {
                case RUSTED -> oldStack.transmuteCopy(ModItems.RUSTED_SHOVEL);
                case WORN -> oldStack.transmuteCopy(ModItems.WORN_SHOVEL);
                case RESTORED -> oldStack.transmuteCopy(ModItems.RESTORED_SHOVEL);
                case PERFECTED -> oldStack.transmuteCopy(ModItems.PERFECTED_SHOVEL);
            };
        };

        newStack.set(
                ModDataComponents.LEGACY_DATA,
                new LegacyToolData(
                        targetStage,
                        0,
                        data.lastInteractionGameTime(),
                        data.abilityStartGameTime(),
                        data.cooldownEndGameTime()
                )
        );

        return newStack;
    }
}