package com.corescode.legacytools.progression;

import com.corescode.legacytools.advancement.LegacyAdvancementHelper;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.legacy.LegacyToolType;
import com.corescode.legacytools.util.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public final class AxeProgressHandler {

    private AxeProgressHandler() {
    }

    public static void handle(Player player, BlockState state) {

        ItemStack stack = player.getMainHandItem();

        if (!LegacyToolUtils.isLegacyAxe(stack)) {
            return;
        }

        ItemStack decayed = LegacyDecayManager.checkDecay(player, stack);

        if (decayed != stack) {

            player.getInventory().setSelectedItem(decayed);
            stack = decayed;
        }

        if (!BlockUtils.isLog(state)) {
            return;
        }

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        data = data.withProgress(data.progress() + 1)
                .withLastInteractionGameTime(player.level().getGameTime());


        if (ProgressManager.shouldUpgrade(
                LegacyToolType.AXE,
                data.stage(),
                data.progress()
        )) {

            LegacyToolData previousData = data;

            stack.set(ModDataComponents.LEGACY_DATA, data);

            ItemStack upgraded = LegacyUpgradeUtils.upgrade(stack);

            player.getInventory().setSelectedItem(upgraded);

            if (player instanceof ServerPlayer serverPlayer) {

                LegacyUpgradeEffects.play(
                        serverPlayer,
                        previousData.stage()
                );

                LegacyAdvancementHelper.onToolUpgrade(
                        serverPlayer,
                        previousData.stage()
                );
            }

            return;
        }

        stack.set(ModDataComponents.LEGACY_DATA, data);
    }
}