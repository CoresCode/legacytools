package com.corescode.legacytools.component;

import com.mojang.serialization.Codec;

import java.util.Arrays;

public enum LegacyStage {

    RUSTED("rusted"),
    WORN("worn"),
    RESTORED("restored"),
    PERFECTED("perfected");

    public static final Codec<LegacyStage> CODEC =
            Codec.STRING.xmap(
                    LegacyStage::fromId,
                    LegacyStage::id
            );

    private final String id;

    LegacyStage(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public LegacyStage next() {
        return switch (this) {
            case RUSTED -> WORN;
            case WORN -> RESTORED;
            case RESTORED -> PERFECTED;
            case PERFECTED -> PERFECTED;
        };
    }

    public LegacyStage previous() {
        return switch (this) {
            case RUSTED -> RUSTED;
            case WORN -> RUSTED;
            case RESTORED -> WORN;
            case PERFECTED -> RESTORED;
        };
    }

    public boolean isPerfected() {
        return this == PERFECTED;
    }

    public static LegacyStage fromId(String id) {
        return Arrays.stream(values())
                .filter(stage -> stage.id.equals(id))
                .findFirst()
                .orElse(RUSTED);
    }
}