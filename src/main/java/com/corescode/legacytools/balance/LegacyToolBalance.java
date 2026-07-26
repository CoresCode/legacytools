package com.corescode.legacytools.balance;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.legacy.LegacyToolType;

public final class LegacyToolBalance {

    private LegacyToolBalance() {
    }

    // Mining  Speed

    public static float getSpeed(LegacyToolType tool, LegacyStage stage) {

        return switch (stage) {

            case RUSTED -> 1.0F;

            case WORN -> 5.0F;

            case RESTORED -> 9.0F;

            case PERFECTED -> 12.0F;
        };
    }

    // Durability

    public static int getDurability(LegacyToolType tool, LegacyStage stage) {

        return switch (stage) {

            case RUSTED -> -1;

            case WORN -> 400;

            case RESTORED -> 1200;

            case PERFECTED -> 2500;
        };
    }

    // Infinite Durability

    public static boolean hasInfiniteDurability(LegacyStage stage) {

        return stage == LegacyStage.RUSTED;
    }

}