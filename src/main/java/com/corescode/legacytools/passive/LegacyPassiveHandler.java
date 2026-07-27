package com.corescode.legacytools.passive;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.item.custom.LegacyAxeItem;
import com.corescode.legacytools.item.custom.LegacyPickaxeItem;
import com.corescode.legacytools.item.custom.LegacyShovelItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.concurrent.ThreadLocalRandom;

public final class LegacyPassiveHandler {

    private LegacyPassiveHandler() {
    }

    public static void handle(
            Level level,
            Player player,
            BlockPos pos,
            BlockState state
    ) {

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        ItemStack stack = player.getMainHandItem();

        if (stack.getItem() instanceof LegacyPickaxeItem) {
            handlePickaxe(player, stack);
        }
        else if (stack.getItem() instanceof LegacyAxeItem) {
            handleAxe(level, serverPlayer, stack, pos);
        }
        else if (stack.getItem() instanceof LegacyShovelItem) {
            handleShovel(level, serverPlayer, stack, pos, state);
        }

    }

    private static void handlePickaxe(
            Player player,
            ItemStack stack
    ) {

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return;
        }

        if (data.stage() != LegacyStage.RESTORED
                && data.stage() != LegacyStage.PERFECTED) {
            return;
        }

        // 2% chance
        if (ThreadLocalRandom.current().nextInt(100) >= 2) {
            return;
        }

        player.addEffect(
                new MobEffectInstance(
                        MobEffects.SPEED,
                        120,
                        1,
                        false,
                        true,
                        true
                )
        );
    }

    private static void handleAxe(
            Level level,
            ServerPlayer player,
            ItemStack stack,
            BlockPos pos
    ) {

        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return;
        }

        if (data.stage() != LegacyStage.RESTORED
                && data.stage() != LegacyStage.PERFECTED) {
            return;
        }

        // 3% chance
        if (ThreadLocalRandom.current().nextInt(100) >= 3) {
            return;
        }

        serverLevel.addFreshEntity(
                new ItemEntity(
                        serverLevel,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        new ItemStack(Items.APPLE)
                )
        );
    }

    private static void handleShovel(
            Level level,
            ServerPlayer player,
            ItemStack stack,
            BlockPos pos,
            BlockState state
    ) {

        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return;
        }

        if (data.stage() != LegacyStage.RESTORED
                && data.stage() != LegacyStage.PERFECTED) {
            return;
        }

        if (!state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL)) {
            return;
        }

        // 2% chance
        if (ThreadLocalRandom.current().nextInt(100) >= 2) {
            return;
        }
        Item loot = getArchaeologyLoot();

        serverLevel.addFreshEntity(
                new ItemEntity(
                        serverLevel,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        new ItemStack(loot)
                )
        );

    }
    private static Item getArchaeologyLoot() {

        int roll = ThreadLocalRandom.current().nextInt(100);

        if (roll < 40) {
            return Items.FLINT;
        }

        if (roll < 60) {
            return Items.BONE;
        }

        if (roll < 80) {
            return Items.CLAY_BALL;
        }

        if (roll < 88) {
            return Items.IRON_NUGGET;
        }

        if (roll < 93) {
            return Items.GOLD_NUGGET;
        }

        if (roll < 96) {
            return Items.EMERALD;
        }

        if (roll < 99) {
            return Items.ANGLER_POTTERY_SHERD;
        }

        return Items.DIAMOND;
    }
}