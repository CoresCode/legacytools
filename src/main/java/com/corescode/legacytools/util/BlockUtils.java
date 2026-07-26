package com.corescode.legacytools.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class BlockUtils {

    private BlockUtils() {
    }

    public static boolean isOre(BlockState state) {

        return state.is(BlockTags.IRON_ORES)
                || state.is(BlockTags.GOLD_ORES)
                || state.is(BlockTags.COPPER_ORES);

    }

    public static boolean isLog(BlockState state) {

        return state.is(BlockTags.LOGS);

    }

    public static boolean isExcavationBlock(BlockState state) {

        return state.is(Blocks.DIRT)
                || state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.COARSE_DIRT)
                || state.is(Blocks.PODZOL)
                || state.is(Blocks.ROOTED_DIRT)
                || state.is(Blocks.MUD)
                || state.is(Blocks.SAND)
                || state.is(Blocks.RED_SAND)
                || state.is(Blocks.GRAVEL)
                || state.is(Blocks.CLAY)
                || state.is(Blocks.SNOW)
                || state.is(Blocks.SNOW_BLOCK)
                || state.is(Blocks.SOUL_SAND)
                || state.is(Blocks.SOUL_SOIL);

    }

}