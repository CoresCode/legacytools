package com.corescode.legacytools.loot; // adjust to your actual package

import com.corescode.legacytools.item.ModItems; // adjust to your actual ModItems location

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;

import net.minecraft.core.HolderLookup;

import net.minecraft.resources.ResourceKey;

import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;

/**
 * Injects Forgotten Tools' rusted tools into vanilla chest loot tables
 * using the Fabric Loot API v3 (LootTableEvents.MODIFY), Mojang mappings.
 */
public final class LegacyLootTableModifier {


    private LegacyLootTableModifier() {
    }

    /**
     * Call once from your mod initializer.
     */
    public static void register() {
        LootTableEvents.MODIFY.register(LegacyLootTableModifier::onModifyLootTable);
    }

    private static void onModifyLootTable(
            ResourceKey<LootTable> key,
            LootTable.Builder tableBuilder,
            LootTableSource source,
            HolderLookup.Provider holder
    ) {
        // Only touch builtin vanilla tables — never override a user's datapack.
        if (!source.isBuiltin()) {
            return;
        }

        if (key.equals(BuiltInLootTables.ABANDONED_MINESHAFT))
                {
            tableBuilder.withPool(createRustedToolsPool());
        }
    }

    /**
     * Builds a fresh loot pool that can produce any one of the three rusted
     * tools. A new builder is created per call since LootPool.Builder is
     * mutable and shouldn't be shared across multiple tables.
     */
    private static LootPool.Builder createRustedToolsPool() {
        return LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.RUSTED_PICKAXE))
                .add(LootItem.lootTableItem(ModItems.RUSTED_AXE))
                .add(LootItem.lootTableItem(ModItems.RUSTED_SHOVEL));
    }
}