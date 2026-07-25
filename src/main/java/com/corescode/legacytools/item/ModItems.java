package com.corescode.legacytools.item;

import com.corescode.legacytools.LegacyTools;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public final class ModItems {

    // Rusted

    public static final Item RUSTED_PICKAXE = registerPickaxe("rusted_pickaxe", ToolMaterial.IRON);
    public static final Item RUSTED_AXE = registerAxe("rusted_axe", ToolMaterial.IRON);
    public static final Item RUSTED_SHOVEL = registerShovel("rusted_shovel", ToolMaterial.IRON);

    // Worn

    public static final Item WORN_PICKAXE = registerPickaxe("worn_pickaxe", ToolMaterial.IRON);
    public static final Item WORN_AXE = registerAxe("worn_axe", ToolMaterial.IRON);
    public static final Item WORN_SHOVEL = registerShovel("worn_shovel", ToolMaterial.IRON);

    // Restored

    public static final Item RESTORED_PICKAXE = registerPickaxe("restored_pickaxe", ToolMaterial.IRON);
    public static final Item RESTORED_AXE = registerAxe("restored_axe", ToolMaterial.IRON);
    public static final Item RESTORED_SHOVEL = registerShovel("restored_shovel", ToolMaterial.IRON);

    // Perfected

    public static final Item PERFECTED_PICKAXE = registerPickaxe("perfected_pickaxe", ToolMaterial.IRON);
    public static final Item PERFECTED_AXE = registerAxe("perfected_axe", ToolMaterial.IRON);
    public static final Item PERFECTED_SHOVEL = registerShovel("perfected_shovel", ToolMaterial.IRON);

    private ModItems() {}

    // Tool Helpers

    private static Item registerPickaxe(String name, ToolMaterial material) {
        return register(
                name,
                properties -> new Item(
                        properties.pickaxe(material, -2.0F, -2.8F)
                )
        );
    }

    private static Item registerAxe(String name, ToolMaterial material) {
        return register(
                name,
                properties -> new AxeItem(
                        material,
                        5.0F,
                        -3.0F,
                        properties
                )
        );
    }

    private static Item registerShovel(String name, ToolMaterial material) {
        return register(
                name,
                properties -> new ShovelItem(
                        material,
                        1.5F,
                        -3.0F,
                        properties
                )
        );
    }

    // Registration


    private static Item register(String name, Function<Item.Properties, Item> factory) {

        ResourceKey<Item> key = ResourceKey.create(
                BuiltInRegistries.ITEM.key(),
                LegacyTools.id(name)
        );

        Item item = factory.apply(
                new Item.Properties().setId(key)
        );

        return Registry.register(
                BuiltInRegistries.ITEM,
                key,
                item
        );
    }

    public static void registerModItems() {
        LegacyTools.LOGGER.info("Registering Legacy Tools items...");
    }
}