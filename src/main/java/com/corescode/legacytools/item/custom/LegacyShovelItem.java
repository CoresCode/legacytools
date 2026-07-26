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

public class LegacyShovelItem extends LegacyToolItem {

    public LegacyShovelItem(Item.Properties properties) {
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
                    LegacyToolType.SHOVEL,
                    data.stage()
            );

            tooltip.accept(
                    Component.literal("Blocks Excavated")
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

        } else {

            tooltip.accept(Component.empty());

            tooltip.accept(
                    Component.literal("The legacy is complete.")
                            .withStyle(ChatFormatting.AQUA)
            );
        }

        tooltip.accept(Component.empty());

        tooltip.accept(
                Component.literal("\"The tool remembers.\"")
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