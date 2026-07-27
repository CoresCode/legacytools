package com.corescode.legacytools.util;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class LegacyDecayManager {

    private static final long PERFECTED_DECAY = 30L * 24000L;
    private static final long RESTORED_DECAY = 40L * 24000L;
    private static final long WORN_DECAY = 50L * 24000L;

    private LegacyDecayManager() {
    }

    public static ItemStack checkDecay(Player player, ItemStack stack) {

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return stack;
        }

        long gameTime = player.level().getGameTime();

        long elapsed = gameTime - data.lastInteractionGameTime();

        LegacyStage currentStage = data.stage();

        long requiredTime = switch (currentStage) {

            case PERFECTED -> PERFECTED_DECAY;
            case RESTORED -> RESTORED_DECAY;
            case WORN -> WORN_DECAY;
            case RUSTED -> Long.MAX_VALUE;
        };

        if (elapsed < requiredTime) {
            return stack;
        }

        LegacyStage newStage = currentStage.previous();

        ItemStack decayed =
                LegacyTransformationUtils.transform(stack, newStage);

        LegacyToolData newData =
                decayed.get(ModDataComponents.LEGACY_DATA);

        if (newData != null) {

            decayed.set(
                    ModDataComponents.LEGACY_DATA,
                    new LegacyToolData(
                            newStage,
                            0,
                            gameTime,
                            newData.abilityStartGameTime(),
                            newData.cooldownEndGameTime()
                    )
            );
        }

        return decayed;
    }
}