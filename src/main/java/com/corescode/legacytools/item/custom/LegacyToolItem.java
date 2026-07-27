package com.corescode.legacytools.item.custom;

import com.corescode.legacytools.balance.LegacyToolBalance;
import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.legacy.LegacyToolType;
import com.corescode.legacytools.util.LegacyToolUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class LegacyToolItem extends Item {

    private static final long ACTIVE_TIME = 300L;
    private static final long COOLDOWN_TIME = 1500L;

    public LegacyToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return super.getDestroySpeed(stack, state);
        }

        LegacyToolType toolType = LegacyToolUtils.getToolType(stack);

        if (toolType == null) {
            return super.getDestroySpeed(stack, state);
        }

        boolean correctTool = switch (toolType) {

            case PICKAXE -> state.is(BlockTags.MINEABLE_WITH_PICKAXE);

            case AXE -> state.is(BlockTags.MINEABLE_WITH_AXE);

            case SHOVEL -> state.is(BlockTags.MINEABLE_WITH_SHOVEL);
        };

        if (!correctTool) {
            return super.getDestroySpeed(stack, state);
        }

        return LegacyToolBalance.getDestroySpeed(toolType, data.stage());
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            return super.use(level, player, hand);
        }

        if (!level.isClientSide()) {

            switch (data.stage()) {

                case RUSTED -> {
                }

                case WORN -> {
                }

                case RESTORED -> player.sendOverlayMessage(
                        Component.literal(
                                        "The " + getToolName(stack) + " has not yet remembered."
                                )
                                .withStyle(ChatFormatting.GOLD)

                );

                case PERFECTED -> {

                    long gameTime = level.getGameTime();

                    if (data.isAbilityActive(gameTime)) {
                        break;
                    }

                    if (data.isOnCooldown(gameTime)) {

                        player.sendOverlayMessage(
                                Component.literal(
                                        "The " + getToolName(stack) + " is not ready for that yet."
                                ).withStyle(ChatFormatting.RED)
                        );

                        break;
                    }

                    stack.set(
                            ModDataComponents.LEGACY_DATA,
                            data.withAbilityStartGameTime(gameTime)
                                    .withLastInteractionGameTime(gameTime)
                                    .withCooldownEndGameTime(gameTime + COOLDOWN_TIME)
                    );

                    player.sendOverlayMessage(
                            Component.literal("Ability Activated")
                                    .withStyle(ChatFormatting.AQUA)
                    );
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    private static String getToolName(ItemStack stack) {

        if (stack.getItem() instanceof LegacyPickaxeItem) {
            return "Pickaxe";
        }

        if (stack.getItem() instanceof LegacyAxeItem) {
            return "Axe";
        }

        if (stack.getItem() instanceof LegacyShovelItem) {
            return "Shovel";
        }

        return "Tool";
    }
}