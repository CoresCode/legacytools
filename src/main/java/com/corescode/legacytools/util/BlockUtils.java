package com.corescode.legacytools.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;

public final class BlockUtils {

    private BlockUtils() {
    }

    public static boolean isOre(BlockState state) {

        return state.is(BlockTags.IRON_ORES)
                || state.is(BlockTags.GOLD_ORES)
                || state.is(BlockTags.COPPER_ORES);

    }

}