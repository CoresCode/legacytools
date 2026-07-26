package com.corescode.legacytools.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.List;

public final class MiningPlaneUtils {

    private MiningPlaneUtils() {
    }

    public static List<BlockPos> getPlane(
            BlockPos center,
            Direction face,
            int radius
    ) {

        ArrayList<BlockPos> blocks = new ArrayList<>();

        Axis first = getBasisA(face);
        Axis second = getBasisB(face);

        for (int a = -radius; a <= radius; a++) {

            for (int b = -radius; b <= radius; b++) {

                if (a == 0 && b == 0) {
                    continue;
                }

                blocks.add(
                        center.offset(
                                first.x() * a + second.x() * b,
                                first.y() * a + second.y() * b,
                                first.z() * a + second.z() * b
                        )
                );
            }
        }

        return blocks;
    }

    private static Axis getBasisA(Direction face) {

        return switch (face) {

            case NORTH, SOUTH -> new Axis(1, 0, 0);

            case EAST, WEST -> new Axis(0, 0, 1);

            case UP, DOWN -> new Axis(1, 0, 0);
        };
    }

    private static Axis getBasisB(Direction face) {

        return switch (face) {

            case NORTH, SOUTH -> new Axis(0, 1, 0);

            case EAST, WEST -> new Axis(0, 1, 0);

            case UP, DOWN -> new Axis(0, 0, 1);
        };
    }

    private record Axis(
            int x,
            int y,
            int z
    ) {
    }
}