package com.corescode.legacytools.util;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.legacy.LegacyToolType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public final class LegacyUpgradeEffects {

    private LegacyUpgradeEffects() {
    }

    public static void play(ServerPlayer player, LegacyStage previousStage) {

        ServerLevel level = player.level();

        Vec3 eye = player.getEyePosition();
        Vec3 look = player.getLookAngle();
        Vec3 right = look.cross(new Vec3(0, 1, 0)).normalize();

        Vec3 particlePos = eye
                .add(look.scale(0.7))
                .add(right.scale(0.35))
                .add(0.0, -0.45, 0.0);

        int dragonCount;
        int endRodCount;
        int glowCount;

        switch (previousStage) {

            case RUSTED -> {
                dragonCount = 60;
                endRodCount = 20;
                glowCount = 10;
            }

            case WORN -> {
                dragonCount = 100;
                endRodCount = 35;
                glowCount = 20;
            }

            case RESTORED -> {
                dragonCount = 160;
                endRodCount = 60;
                glowCount = 35;
            }

            default -> {
                dragonCount = 0;
                endRodCount = 0;
                glowCount = 0;
            }
        }

        level.sendParticles(
                PowerParticleOption.create(
                        ParticleTypes.DRAGON_BREATH,
                        1.0F
                ),
                particlePos.x,
                particlePos.y,
                particlePos.z,
                dragonCount,
                0.18,
                0.18,
                0.18,
                0.05
        );

        level.sendParticles(
                ParticleTypes.END_ROD,
                particlePos.x,
                particlePos.y,
                particlePos.z,
                endRodCount,
                0.22,
                0.22,
                0.22,
                0.03
        );

        level.sendParticles(
                ParticleTypes.GLOW,
                particlePos.x,
                particlePos.y,
                particlePos.z,
                glowCount,
                0.12,
                0.12,
                0.12,
                0.02
        );

        level.playSound(
                null,
                player.blockPosition(),
                SoundEvents.PLAYER_LEVELUP,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );

        ItemStack stack = player.getMainHandItem();
        LegacyToolType toolType = LegacyToolUtils.getToolType(stack);

        String toolName = switch (toolType) {
            case PICKAXE -> "Pickaxe";
            case AXE -> "Axe";
            case SHOVEL -> "Shovel";
        };

        Component message = switch (previousStage) {

            case RUSTED ->
                    Component.literal("The " + toolName + " is remembering...")
                            .withStyle(ChatFormatting.GRAY);

            case WORN ->
                    Component.literal("The " + toolName + " has been restored.")
                            .withStyle(ChatFormatting.GOLD);

            case RESTORED ->
                    Component.literal("The " + toolName + " remembers its legacy.")
                            .withStyle(ChatFormatting.AQUA);

            case PERFECTED ->
                    Component.empty();
        };

        if (!message.getString().isEmpty()) {
            player.sendOverlayMessage(message);
        }
    }
}