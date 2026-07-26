package com.corescode.legacytools.world;

import net.minecraft.core.Direction;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class LegacyMiningContext {

    private static final Map<UUID, Direction> LAST_FACE = new ConcurrentHashMap<>();

    private LegacyMiningContext() {
    }

    public static void set(UUID playerId, Direction direction) {
        LAST_FACE.put(playerId, direction);
    }

    public static Direction get(UUID playerId) {
        return LAST_FACE.get(playerId);
    }

    public static void clear(UUID playerId) {
        LAST_FACE.remove(playerId);
    }
}