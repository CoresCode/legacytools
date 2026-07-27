package com.corescode.legacytools.tag;

import com.corescode.legacytools.LegacyTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModBlockTags {

    private ModBlockTags() {
    }

    public static final TagKey<Block> RUSTED_PICKAXE_MINEABLE =
            TagKey.create(
                    Registries.BLOCK,
                    LegacyTools.id("rusted_pickaxe_mineable")
            );

    public static final TagKey<Block> WORN_PICKAXE_MINEABLE =
            TagKey.create(
                    Registries.BLOCK,
                    LegacyTools.id("worn_pickaxe_mineable")
            );

    public static final TagKey<Block> RESTORED_PICKAXE_DENIED =
            TagKey.create(
                    Registries.BLOCK,
                    LegacyTools.id("restored_pickaxe_denied")
            );

}