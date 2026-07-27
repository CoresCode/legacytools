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

import com.corescode.legacytools.legacy.LegacyToolType;
import com.corescode.legacytools.tool.LegacyToolComponentFactory;
import net.minecraft.core.component.DataComponents;
import com.corescode.legacytools.tool.LegacyToolMaterials;
import java.util.function.Function;

public final class ModItems {

    // Rusted

    public static final Item RUSTED_PICKAXE = registerPickaxe("rusted_pickaxe", LegacyToolMaterials.RUSTED, LegacyStage.RUSTED);
    public static final Item RUSTED_AXE = registerAxe("rusted_axe", LegacyToolMaterials.RUSTED, LegacyStage.RUSTED);
    public static final Item RUSTED_SHOVEL = registerShovel("rusted_shovel", LegacyToolMaterials.RUSTED, LegacyStage.RUSTED);

// Worn

    public static final Item WORN_PICKAXE = registerPickaxe("worn_pickaxe", LegacyToolMaterials.WORN, LegacyStage.WORN);
    public static final Item WORN_AXE = registerAxe("worn_axe", LegacyToolMaterials.WORN, LegacyStage.WORN);
    public static final Item WORN_SHOVEL = registerShovel("worn_shovel", LegacyToolMaterials.WORN, LegacyStage.WORN);

// Restored

    public static final Item RESTORED_PICKAXE = registerPickaxe("restored_pickaxe", LegacyToolMaterials.RESTORED, LegacyStage.RESTORED);
    public static final Item RESTORED_AXE = registerAxe("restored_axe", LegacyToolMaterials.RESTORED, LegacyStage.RESTORED);
    public static final Item RESTORED_SHOVEL = registerShovel("restored_shovel", LegacyToolMaterials.RESTORED, LegacyStage.RESTORED);

// Perfected

    public static final Item PERFECTED_PICKAXE = registerPickaxe("perfected_pickaxe", LegacyToolMaterials.PERFECTED, LegacyStage.PERFECTED);
    public static final Item PERFECTED_AXE = registerAxe("perfected_axe", LegacyToolMaterials.PERFECTED, LegacyStage.PERFECTED);
    public static final Item PERFECTED_SHOVEL = registerShovel("perfected_shovel", LegacyToolMaterials.PERFECTED, LegacyStage.PERFECTED);

    private ModItems() {
    }

    private static Item registerPickaxe(String name, ToolMaterial material, LegacyStage stage) {
        return register(
                name,
                properties -> new LegacyPickaxeItem(
                        properties
                                .pickaxe(
                                        material,
                                        switch (stage) {
                                            case RUSTED -> 0.0F;
                                            case WORN -> -2.0F;
                                            case RESTORED -> -1.0F;
                                            case PERFECTED -> 0.0F;
                                        },
                                        switch (stage) {
                                            case RUSTED -> -2.8F;
                                            case WORN -> -2.8F;
                                            case RESTORED -> -2.9F;
                                            case PERFECTED -> -3.0F;
                                        }
                                )
                                .component(
                                        DataComponents.TOOL,
                                        LegacyToolComponentFactory.create(
                                                LegacyToolType.PICKAXE,
                                                stage
                                        )
                                )
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
                                .axe(
                                        material,
                                        switch (stage) {
                                            case RUSTED -> 3.0F;
                                            case WORN -> 4.0F;
                                            case RESTORED -> 5.0F;
                                            case PERFECTED -> 6.0F;
                                        },
                                        switch (stage) {
                                            case RUSTED -> -3.3F;
                                            case WORN -> -3.2F;
                                            case RESTORED -> -3.1F;
                                            case PERFECTED -> -3.0F;
                                        }
                                )
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
                                .shovel(
                                        material,
                                        switch (stage) {
                                            case RUSTED -> 2.0F;
                                            case WORN -> 2.5F;
                                            case RESTORED -> 3.0F;
                                            case PERFECTED -> 3.5F;
                                        },
                                        -3.0F
                                )
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