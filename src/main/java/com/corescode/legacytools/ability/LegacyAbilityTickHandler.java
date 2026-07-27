package com.corescode.legacytools.ability;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;


import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class LegacyAbilityTickHandler {

    private LegacyAbilityTickHandler() {
    }

    private static final ConcurrentHashMap<UUID, Integer> LAST_SECOND =
            new ConcurrentHashMap<>();

    public static void tick(MinecraftServer server) {

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {

            tickPlayer(player);

        }
    }

    private static void tickPlayer(ServerPlayer player) {

        ItemStack stack = player.getMainHandItem();

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return;
        }

        if (data.stage() != LegacyStage.PERFECTED) {
            return;
        }

        long gameTime = player.level().getGameTime();

        if (!data.isAbilityActive(gameTime)) {

            LAST_SECOND.remove(player.getUUID());

            return;
        }

        long remainingTicks =
                (data.abilityStartGameTime() + 300L) - gameTime;

        int remainingSeconds =
                (int) Math.ceil(remainingTicks / 20.0);

        if (remainingSeconds == 15) {
            return;
        }

        Integer previousSecond =
                LAST_SECOND.get(player.getUUID());

        if (previousSecond == null || previousSecond != remainingSeconds) {

            LAST_SECOND.put(player.getUUID(), remainingSeconds);


            player.sendOverlayMessage(
                    Component.literal(
                            "Ability: " + remainingSeconds
                    ).withStyle(ChatFormatting.AQUA)
            );
        }
    }
}