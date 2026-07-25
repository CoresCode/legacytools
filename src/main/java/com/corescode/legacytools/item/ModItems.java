package com.corescode.legacytools.item;

import com.corescode.legacytools.LegacyTools;
import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.item.custom.LegacyAxeItem;
import com.corescode.legacytools.item.custom.LegacyPickaxeItem;
import com.corescode.legacytools.item.custom.LegacyShovelItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public final class ModItems {

    // Rusted

    public static final Item RUSTED_PICKAXE = registerPickaxe("rusted_pickaxe", ToolMaterial.IRON, LegacyStage.RUSTED);
    public static final Item RUSTED_AXE = registerAxe("rusted_axe", ToolMaterial.IRON, LegacyStage.RUSTED);
    public static final Item RUSTED_SHOVEL = registerShovel("rusted_shovel", ToolMaterial.IRON, LegacyStage.RUSTED);

    // Worn

    public static final Item WORN_PICKAXE = registerPickaxe("worn_pickaxe", ToolMaterial.IRON, LegacyStage.WORN);
    public static final Item WORN_AXE = registerAxe("worn_axe", ToolMaterial.IRON, LegacyStage.WORN);
    public static final Item WORN_SHOVEL = registerShovel("worn_shovel", ToolMaterial.IRON, LegacyStage.WORN);

    // Restored

    public static final Item RESTORED_PICKAXE = registerPickaxe("restored_pickaxe", ToolMaterial.IRON, LegacyStage.RESTORED);
    public static final Item RESTORED_AXE = registerAxe("restored_axe", ToolMaterial.IRON, LegacyStage.RESTORED);
    public static final Item RESTORED_SHOVEL = registerShovel("restored_shovel", ToolMaterial.IRON, LegacyStage.RESTORED);

    // Perfected

    public static final Item PERFECTED_PICKAXE = registerPickaxe("perfected_pickaxe", ToolMaterial.IRON, LegacyStage.PERFECTED);
    public static final Item PERFECTED_AXE = registerAxe("perfected_axe", ToolMaterial.IRON, LegacyStage.PERFECTED);
    public static final Item PERFECTED_SHOVEL = registerShovel("perfected_shovel", ToolMaterial.IRON, LegacyStage.PERFECTED);

    private ModItems() {
    }

    private static Item registerPickaxe(String name, ToolMaterial material, LegacyStage stage) {
        return register(
                name,
                properties -> new LegacyPickaxeItem(
                        properties
                                .pickaxe(material, -2.0F, -2.8F)
                                .component(
                                        ModDataComponents.LEGACY_DATA,
                                        new LegacyToolData(
                                                stage,
                                                0,
                                                0L,
                                                0L
                                        )
                                )
                )
        );
    }

    private static Item registerAxe(String name, ToolMaterial material, LegacyStage stage) {
        return register(
                name,
                properties -> new LegacyAxeItem(
                        properties
                                .component(
                                        ModDataComponents.LEGACY_DATA,
                                        new LegacyToolData(
                                                stage,
                                                0,
                                                0L,
                                                0L
                                        )
                                )
                )
        );
    }

    private static Item registerShovel(String name, ToolMaterial material, LegacyStage stage) {
        return register(
                name,
                properties -> new LegacyShovelItem(
                        properties
                                .component(
                                        ModDataComponents.LEGACY_DATA,
                                        new LegacyToolData(
                                                stage,
                                                0,
                                                0L,
                                                0L
                                        )
                                )
                )
        );
    }

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