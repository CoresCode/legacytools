package com.corescode.legacytools.item.custom;

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

        tooltip.accept(Component.empty());

        tooltip.accept(
                Component.literal("Stage: ")
                        .withStyle(ChatFormatting.GRAY)
                        .append(
                                Component.literal("Rusted")
                                        .withStyle(ChatFormatting.DARK_RED)
                        )
        );

        tooltip.accept(
                Component.literal("Progress: ")
                        .withStyle(ChatFormatting.GRAY)
                        .append(
                                Component.literal("0 / 20")
                                        .withStyle(ChatFormatting.GOLD)
                        )
        );

        tooltip.accept(Component.empty());

        tooltip.accept(
                Component.literal("\"The tool remembers.\"")
                        .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC)
        );

        super.appendHoverText(stack, context, display, tooltip, flag);
    }
}