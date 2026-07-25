package com.corescode.legacytools.progression;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.legacy.LegacyConstants;
import com.corescode.legacytools.legacy.LegacyToolType;

public final class ProgressManager {

    private ProgressManager() {
    }

    public static int getRequiredProgress(LegacyToolType toolType, LegacyStage stage) {

        return switch (toolType) {

            case PICKAXE -> switch (stage) {
                case RUSTED -> LegacyConstants.PICKAXE_STAGE_2;
                case WORN -> LegacyConstants.PICKAXE_STAGE_3;
                case RESTORED -> LegacyConstants.PICKAXE_STAGE_4;
                case PERFECTED -> Integer.MAX_VALUE;
            };

            case AXE -> switch (stage) {
                case RUSTED -> LegacyConstants.AXE_STAGE_2;
                case WORN -> LegacyConstants.AXE_STAGE_3;
                case RESTORED -> LegacyConstants.AXE_STAGE_4;
                case PERFECTED -> Integer.MAX_VALUE;
            };

            case SHOVEL -> switch (stage) {
                case RUSTED -> LegacyConstants.SHOVEL_STAGE_2;
                case WORN -> LegacyConstants.SHOVEL_STAGE_3;
                case RESTORED -> LegacyConstants.SHOVEL_STAGE_4;
                case PERFECTED -> Integer.MAX_VALUE;
            };
        };
    }

    public static boolean shouldUpgrade(
            LegacyToolType toolType,
            LegacyStage stage,
            int progress
    ) {
        return progress >= getRequiredProgress(toolType, stage);
    }

}