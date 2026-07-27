package com.corescode.legacytools.advancement;

import com.corescode.legacytools.component.LegacyStage;
import net.minecraft.resources.Identifier;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.advancements.AdvancementHolder;

public final class LegacyAdvancementHelper {

    private static final Identifier LEGACY_AWAKENED =
            Identifier.fromNamespaceAndPath("legacytools", "legacy_awakened");

    private static final Identifier RESTORING_HISTORY =
            Identifier.fromNamespaceAndPath("legacytools", "restoring_history");

    private static final Identifier LEGACY_PERFECTED =
            Identifier.fromNamespaceAndPath("legacytools", "legacy_perfected");

    private LegacyAdvancementHelper() {
    }

    public static void onToolUpgrade(ServerPlayer player, LegacyStage previousStage) {

        switch (previousStage) {

            case RUSTED -> grant(player, RESTORING_HISTORY);

            case RESTORED -> grant(player, LEGACY_PERFECTED);

            default -> {
                // No advancement
            }
        }
    }

    private static void grant(ServerPlayer player, Identifier id) {

        AdvancementHolder advancement =
                player.level().getServer().getAdvancements().get(id);

        if (advancement == null) {
            return;
        }

        PlayerAdvancements progress = player.getAdvancements();

        if (progress.getOrStartProgress(advancement).isDone()) {
            return;
        }

        for (String criterion : progress.getOrStartProgress(advancement).getRemainingCriteria()) {
            progress.award(advancement, criterion);
        }
    }
}