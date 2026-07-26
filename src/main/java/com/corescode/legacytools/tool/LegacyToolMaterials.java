package com.corescode.legacytools.tool;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public final class LegacyToolMaterials {

    private LegacyToolMaterials() {
    }

    public static final ToolMaterial RUSTED =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_IRON_TOOL,
                    1,
                    2.0F,
                    2.0F,
                    100,
                    ItemTags.IRON_TOOL_MATERIALS
            );

    public static final ToolMaterial WORN =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_IRON_TOOL,
                    250,
                    6.0F,
                    2.0F,
                    14,
                    ItemTags.IRON_TOOL_MATERIALS
            );

    public static final ToolMaterial RESTORED =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                    1561,
                    8.0F,
                    3.0F,
                    10,
                    ItemTags.DIAMOND_TOOL_MATERIALS
            );

    public static final ToolMaterial PERFECTED =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                    4096,
                    9.0F,
                    4.0F,
                    15,
                    ItemTags.NETHERITE_TOOL_MATERIALS
            );
}