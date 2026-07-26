package com.corescode.legacytools.ability;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public final class LegacyTreeSearch {

    public static final int MAX_LOGS = 130;

    private LegacyTreeSearch() {
    }

    public static Set<BlockPos> findTree(
            ServerLevel level,
            BlockPos start
    ) {

        // The broken block is now air.
        // Find a neighboring log to begin the search.
        BlockPos root = null;

        for (Direction direction : Direction.values()) {

            BlockPos neighbor = start.relative(direction);

            if (level.getBlockState(neighbor).is(BlockTags.LOGS)) {
                root = neighbor;
                break;
            }

        }

        if (root == null) {
            return Collections.emptySet();
        }

        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();

        visited.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {

            BlockPos current = queue.remove();

            if (visited.size() >= MAX_LOGS) {
                break;
            }

            for (Direction direction : Direction.values()) {

                BlockPos next = current.relative(direction);

                if (visited.contains(next)) {
                    continue;
                }

                if (!level.getBlockState(next).is(BlockTags.LOGS)) {
                    continue;
                }

                visited.add(next);
                queue.add(next);

            }

        }

        if (!hasNearbyLeaves(level, visited)) {
            return Collections.emptySet();
        }

        return visited;
    }

    private static boolean hasNearbyLeaves(
            ServerLevel level,
            Set<BlockPos> logs
    ) {

        for (BlockPos log : logs) {

            for (Direction direction : Direction.values()) {

                BlockPos neighbor = log.relative(direction);

                if (level.getBlockState(neighbor).is(BlockTags.LEAVES)) {
                    return true;
                }

            }

        }

        return false;
    }
}