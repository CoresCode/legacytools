package com.corescode.legacytools.item.custom;

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

public class LegacyPickaxeItem extends Item {

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

        int requiredProgress = ProgressManager.getRequiredProgress(
                LegacyToolType.PICKAXE,
                data.stage()
        );

        tooltip.accept(
                Component.literal("Stage: ")
                        .withStyle(ChatFormatting.GRAY)
                        .append(
                                Component.literal(capitalize(data.stage().id()))
                                        .withStyle(ChatFormatting.DARK_RED)
                        )
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

        tooltip.accept(Component.empty());

        tooltip.accept(
                Component.literal("\"The tool remembers.\"")
                        .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC)
        );

        super.appendHoverText(stack, context, display, tooltip, flag);
    }

    private static String capitalize(String text) {
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }
}