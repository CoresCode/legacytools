package com.corescode.legacytools.loot;

import com.corescode.legacytools.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public final class LegacyLootTableModifier {

    private LegacyLootTableModifier() {
    }

    public static void register() {
        LootTableEvents.MODIFY.register(LegacyLootTableModifier::onModifyLootTable);
    }

    private static void onModifyLootTable(
            ResourceKey<LootTable> key,
            LootTable.Builder tableBuilder,
            LootTableSource source,
            HolderLookup.Provider holder
    ) {

        if (!source.isBuiltin()) {
            return;
        }

        // Abandoned Mineshaft
        if (key.equals(BuiltInLootTables.ABANDONED_MINESHAFT)) {
            tableBuilder.withPool(createLootPool(50, 17, 16, 15));
        }

        // Ancient City
        else if (key.equals(BuiltInLootTables.ANCIENT_CITY)) {
            tableBuilder.withPool(createLootPool(55, 15, 15, 15));
        }

        // Woodland Mansion
        else if (key.equals(BuiltInLootTables.WOODLAND_MANSION)) {
            tableBuilder.withPool(createLootPool(59, 10, 15, 10));
        }

        // Stronghold
        else if (
                key.equals(BuiltInLootTables.STRONGHOLD_CORRIDOR)
                        || key.equals(BuiltInLootTables.STRONGHOLD_CROSSING)
                        || key.equals(BuiltInLootTables.STRONGHOLD_LIBRARY)
        ) {
            tableBuilder.withPool(createLootPool(65, 11, 11, 11));
        }

        // Dungeon
        else if (key.equals(BuiltInLootTables.SIMPLE_DUNGEON)) {
            tableBuilder.withPool(createLootPool(65, 11, 11, 12));
        }

        // Desert Pyramid
        else if (key.equals(BuiltInLootTables.DESERT_PYRAMID)) {
            tableBuilder.withPool(createLootPool(74, 7, 8, 9));
        }

        // Jungle Temple
        else if (key.equals(BuiltInLootTables.JUNGLE_TEMPLE)) {
            tableBuilder.withPool(createLootPool(74, 9, 8, 9));
        }

        // Trial Chambers
        else if (
                key.equals(BuiltInLootTables.TRIAL_CHAMBERS_ENTRANCE)
                        || key.equals(BuiltInLootTables.TRIAL_CHAMBERS_CORRIDOR)
                        || key.equals(BuiltInLootTables.TRIAL_CHAMBERS_INTERSECTION)
        ) {
            tableBuilder.withPool(createLootPool(71, 10, 9, 10));
        }
    }

    private static LootPool.Builder createLootPool(
            int emptyWeight,
            int pickaxeWeight,
            int axeWeight,
            int shovelWeight
    ) {
        return LootPool.lootPool()
                .add(EmptyLootItem.emptyItem().setWeight(emptyWeight))

                .add(LootItem.lootTableItem(ModItems.RUSTED_PICKAXE).setWeight(pickaxeWeight))
                .add(LootItem.lootTableItem(ModItems.RUSTED_AXE).setWeight(axeWeight))
                .add(LootItem.lootTableItem(ModItems.RUSTED_SHOVEL).setWeight(shovelWeight));
    }
}