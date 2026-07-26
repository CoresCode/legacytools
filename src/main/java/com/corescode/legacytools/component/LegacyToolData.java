package com.corescode.legacytools.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record LegacyToolData(
        LegacyStage stage,
        int progress,
        long lastUsedGameTime,
        long cooldownEndGameTime
) {

    public static final Codec<LegacyToolData> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(

                    LegacyStage.CODEC
                            .fieldOf("stage")
                            .forGetter(LegacyToolData::stage),

                    Codec.INT
                            .fieldOf("progress")
                            .forGetter(LegacyToolData::progress),

                    Codec.LONG
                            .fieldOf("last_used_game_time")
                            .forGetter(LegacyToolData::lastUsedGameTime),

                    Codec.LONG
                            .fieldOf("cooldown_end_game_time")
                            .forGetter(LegacyToolData::cooldownEndGameTime)

            ).apply(instance, LegacyToolData::new));

    public static final LegacyToolData DEFAULT =
            new LegacyToolData(
                    LegacyStage.RUSTED,
                    0,
                    0L,
                    0L
            );

    public LegacyToolData withStage(LegacyStage stage) {
        return new LegacyToolData(stage, progress, lastUsedGameTime, cooldownEndGameTime);
    }

    public LegacyToolData withProgress(int progress) {
        return new LegacyToolData(stage, progress, lastUsedGameTime, cooldownEndGameTime);
    }

    public LegacyToolData withLastUsedGameTime(long gameTime) {
        return new LegacyToolData(stage, progress, gameTime, cooldownEndGameTime);
    }

    public LegacyToolData withCooldownEndGameTime(long gameTime) {
        return new LegacyToolData(stage, progress, lastUsedGameTime, gameTime);
    }

    public boolean isAbilityActive(long gameTime) {
        return lastUsedGameTime > 0L
                && gameTime < lastUsedGameTime + 300L;
    }

    public boolean isOnCooldown(long gameTime) {
        return gameTime < cooldownEndGameTime;
    }
}