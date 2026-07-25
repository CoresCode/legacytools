package com.corescode.legacytools.progression;

import com.corescode.legacytools.component.LegacyToolData;
import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.item.ModItems;
import com.corescode.legacytools.util.BlockUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public final class MiningProgressHandler {

    private MiningProgressHandler() {
    }

    public static void handle(Player player, BlockState state) {

        ItemStack stack = player.getMainHandItem();

        if (!stack.is(ModItems.RUSTED_PICKAXE)) {
            return;
        }

        if (!BlockUtils.isOre(state)) {
            return;
        }

        LegacyToolData data = stack.get(ModDataComponents.LEGACY_DATA);

        if (data == null) {
            data = LegacyToolData.DEFAULT;
        }

        data = data.withProgress(data.progress() + 1);

        stack.set(ModDataComponents.LEGACY_DATA, data);
    }
}