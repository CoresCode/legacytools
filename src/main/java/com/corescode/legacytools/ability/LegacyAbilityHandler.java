package com.corescode.legacytools.ability;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.item.custom.LegacyAxeItem;
import com.corescode.legacytools.item.custom.LegacyPickaxeItem;
import com.corescode.legacytools.item.custom.LegacyShovelItem;
import com.corescode.legacytools.util.MiningPlaneUtils;
import com.corescode.legacytools.world.LegacyMiningContext;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public final class LegacyAbilityHandler {

    private LegacyAbilityHandler() {
    }

    public static void handle(
            Level level,
            Player player,
            BlockPos pos,
            BlockState state
    ) {

        if (LegacyAbilityContext.isBreaking()) {
            return;
        }

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        ItemStack stack = player.getMainHandItem();

        if (stack.getItem() instanceof LegacyPickaxeItem) {
            handlePickaxe(serverPlayer, stack, level, pos, state);
            return;
        }

        if (stack.getItem() instanceof LegacyAxeItem) {
            handleAxe(serverPlayer, stack, level, pos, state);
            return;
        }

        if (stack.getItem() instanceof LegacyShovelItem) {
            handleShovel(serverPlayer, stack, level, pos, state);
        }
    }

    private static void handlePickaxe(
            ServerPlayer player,
            ItemStack stack,
            Level level,
            BlockPos pos,
            BlockState state
    ) {

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return;
        }

        if (data.stage() != LegacyStage.PERFECTED) {
            return;
        }

        if (!data.isAbilityActive(level.getGameTime())) {
            return;
        }

        Direction face = LegacyMiningContext.get(player.getUUID());

        if (face == null) {
            return;
        }

        LegacyMiningContext.clear(player.getUUID());

        LegacyAbilityContext.beginBreaking();

        try {

            for (BlockPos target : MiningPlaneUtils.getPlane(pos, face, 1)) {

                BlockState targetState = level.getBlockState(target);

                if (targetState.isAir()) {
                    continue;
                }

                if (targetState.getDestroySpeed(level, target) < 0.0F) {
                    continue;
                }

                if (!targetState.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE)) {
                    continue;
                }

                if (!player.hasCorrectToolForDrops(targetState)) {
                    continue;
                }

                player.gameMode.destroyBlock(target);
            }

        } finally {

            LegacyAbilityContext.endBreaking();

        }
    }

    private static void handleAxe(
            ServerPlayer player,
            ItemStack stack,
            Level level,
            BlockPos pos,
            BlockState state
    ) {

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return;
        }


        if (data.stage() != LegacyStage.PERFECTED) {
            return;
        }

        if (!data.isAbilityActive(level.getGameTime())) {

            return;
        }


        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        Set<BlockPos> tree =
                LegacyTreeSearch.findTree(serverLevel, pos);

        if (tree.isEmpty()) {
            return;
        }
        for (BlockPos log : tree) {

            player.gameMode.destroyBlock(log);

        }

    }

    private static void handleShovel(
            ServerPlayer player,
            ItemStack stack,
            Level level,
            BlockPos pos,
            BlockState state
    ) {

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return;
        }

        if (data.stage() != LegacyStage.PERFECTED) {
            return;
        }

        if (!data.isAbilityActive(level.getGameTime())) {
            return;
        }

        Direction face = LegacyMiningContext.get(player.getUUID());

        if (face == null) {
            return;
        }

        LegacyMiningContext.clear(player.getUUID());

        LegacyAbilityContext.beginBreaking();

        try {

            for (BlockPos target : MiningPlaneUtils.getPlane(pos, face, 1)) {

                BlockState targetState = level.getBlockState(target);

                if (targetState.isAir()) {
                    continue;
                }

                if (targetState.getDestroySpeed(level, target) < 0.0F) {
                    continue;
                }

                if (!targetState.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL)) {
                    continue;
                }

                if (!player.hasCorrectToolForDrops(targetState)) {
                    continue;
                }

                player.gameMode.destroyBlock(target);

            }

        } finally {

            LegacyAbilityContext.endBreaking();

        }

    }
}