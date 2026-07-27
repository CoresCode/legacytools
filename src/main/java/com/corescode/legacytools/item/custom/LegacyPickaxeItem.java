package com.corescode.legacytools.item.custom;

import com.corescode.legacytools.component.LegacyStage;
import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.legacy.LegacyToolType;
import com.corescode.legacytools.progression.ProgressManager;
import com.corescode.legacytools.util.ProgressBarUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class LegacyPickaxeItem extends LegacyToolItem {

    public LegacyPickaxeItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            TooltipDisplay display,
            Consumer<Component> tooltip,
            TooltipFlag flag
    ) {

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        tooltip.accept(
                Component.literal("Stage: ")
                        .withStyle(ChatFormatting.GRAY)
                        .append(
                                Component.literal(capitalize(data.stage().id()))
                                        .withStyle(getStageColor(data.stage()))
                        )
        );

        if (data.stage() != LegacyStage.PERFECTED) {

            int requiredProgress = ProgressManager.getRequiredProgress(
                    LegacyToolType.PICKAXE,
                    data.stage()
            );

            tooltip.accept(
                    Component.literal("Ores Mined")
                            .withStyle(ChatFormatting.GRAY)
            );

            tooltip.accept(
                    Component.literal(
                            ProgressBarUtils.createProgressBar(
                                    data.progress(),
                                    requiredProgress
                            )
                    ).withStyle(ChatFormatting.GREEN)
            );

            tooltip.accept(
                    Component.literal(
                            data.progress() + " / " + requiredProgress
                    ).withStyle(ChatFormatting.GOLD)
            );

            if (data.stage() == LegacyStage.RESTORED) {

                tooltip.accept(Component.empty());

                tooltip.accept(
                        Component.literal("Passive Ability")
                                .withStyle(ChatFormatting.BLUE)
                );

                tooltip.accept(
                        Component.literal("Ancient Instinct")
                                .withStyle(ChatFormatting.GOLD)
                );

                tooltip.accept(
                        Component.literal("Small chance to gain Speed for a short while after mining.")
                                .withStyle(ChatFormatting.GRAY)
                );
            }

        } else {

            tooltip.accept(Component.empty());

            tooltip.accept(
                    Component.literal("The legacy lives on.")
                            .withStyle(ChatFormatting.DARK_PURPLE)
            );

            tooltip.accept(Component.empty());

            tooltip.accept(
                    Component.literal("Passive Ability")
                            .withStyle(ChatFormatting.BLUE)
            );

            tooltip.accept(
                    Component.literal("Ancient Instinct")
                            .withStyle(ChatFormatting.GOLD)
            );

            tooltip.accept(
                    Component.literal("Small chance to gain Speed for a short time after mining.")
                            .withStyle(ChatFormatting.GRAY)
            );

            tooltip.accept(Component.empty());

            tooltip.accept(
                    Component.literal("Active Ability")
                            .withStyle(ChatFormatting.BLUE)
            );

            tooltip.accept(
                    Component.literal("Echoes of the Caves")
                            .withStyle(ChatFormatting.GOLD)
            );

            tooltip.accept(
                    Component.literal("Right Click to awaken the echoes.")
                            .withStyle(ChatFormatting.GRAY)
            );

            tooltip.accept(Component.empty());

            tooltip.accept(
                    Component.literal("Duration: 15 Seconds")
                            .withStyle(ChatFormatting.GREEN)
            );

            tooltip.accept(
                    Component.literal("Cooldown: 60 Seconds")
                            .withStyle(ChatFormatting.RED)
            );
        }

        tooltip.accept(Component.empty());

        tooltip.accept(
                Component.literal("\"An ancient tool.\"")
                        .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC)
        );

        super.appendHoverText(stack, context, display, tooltip, flag);
    }

    private static ChatFormatting getStageColor(LegacyStage stage) {
        return switch (stage) {
            case RUSTED -> ChatFormatting.DARK_RED;
            case WORN -> ChatFormatting.YELLOW;
            case RESTORED -> ChatFormatting.GOLD;
            case PERFECTED -> ChatFormatting.AQUA;
        };
    }

    private static String capitalize(String text) {
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }
}