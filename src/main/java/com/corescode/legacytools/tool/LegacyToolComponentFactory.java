package com.corescode.legacytools.tool;

import com.corescode.legacytools.balance.LegacyToolBalance;
import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.legacy.LegacyToolType;
import com.corescode.legacytools.tag.ModBlockTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;

import java.util.List;

public final class LegacyToolComponentFactory {

    private LegacyToolComponentFactory() {
    }

    public static Tool create(LegacyToolType toolType, LegacyStage stage) {

        HolderGetter<Block> lookup =
                BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);

        float speed = LegacyToolBalance.getSpeed(toolType, stage);

        return switch (toolType) {

            case PICKAXE -> switch (stage) {

                case RUSTED -> new Tool(
                        List.of(
                                Tool.Rule.deniesDrops(
                                        lookup.getOrThrow(BlockTags.INCORRECT_FOR_IRON_TOOL)
                                ),
                                Tool.Rule.minesAndDrops(
                                        lookup.getOrThrow(ModBlockTags.RUSTED_PICKAXE_MINEABLE),
                                        speed
                                )
                        ),
                        1.0F,
                        1,
                        true
                );

                case WORN -> new Tool(
                        List.of(
                                Tool.Rule.deniesDrops(
                                        lookup.getOrThrow(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                                ),
                                Tool.Rule.minesAndDrops(
                                        lookup.getOrThrow(ModBlockTags.WORN_PICKAXE_MINEABLE),
                                        speed
                                )
                        ),
                        1.0F,
                        1,
                        true
                );

                case RESTORED -> new Tool(
                        List.of(
                                Tool.Rule.deniesDrops(
                                        lookup.getOrThrow(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                                ),
                                Tool.Rule.deniesDrops(
                                        lookup.getOrThrow(ModBlockTags.RESTORED_PICKAXE_DENIED)
                                ),

                                Tool.Rule.minesAndDrops(
                                        lookup.getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE),
                                        speed
                                )
                        ),
                        1.0F,
                        1,
                        true
                );

                case PERFECTED -> new Tool(
                        List.of(
                                Tool.Rule.minesAndDrops(
                                        lookup.getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE),
                                        speed
                                )
                        ),
                        1.0F,
                        1,
                        true
                );
            };

            case AXE -> throw new UnsupportedOperationException("Axe tool rules not implemented yet.");

            case SHOVEL -> throw new UnsupportedOperationException("Shovel tool rules not implemented yet.");
        };
    }
}