package com.corescode.legacytools.item;

import com.corescode.legacytools.LegacyTools;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public final class ModItems {

    public static final Item RUSTED_PICKAXE = register(
            "rusted_pickaxe",
            new Item(
                    new Item.Properties().pickaxe(ToolMaterial.IRON, 1.0F, -2.8F)
            )
    );

    private ModItems() {
    }

    private static Item register(String name, Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                LegacyTools.id(name),
                item
        );
    }

    public static void registerModItems() {
        LegacyTools.LOGGER.info("Registering Legacy Tools items...");
    }
}