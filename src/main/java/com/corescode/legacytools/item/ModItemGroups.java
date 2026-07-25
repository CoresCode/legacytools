package com.corescode.legacytools.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;

public final class ModItemGroups {

    private ModItemGroups() {
    }

    public static void registerItemGroups() {

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(entries -> {

                    entries.accept(ModItems.RUSTED_PICKAXE);
                    entries.accept(ModItems.RUSTED_AXE);
                    entries.accept(ModItems.RUSTED_SHOVEL);

                    entries.accept(ModItems.WORN_PICKAXE);
                    entries.accept(ModItems.WORN_AXE);
                    entries.accept(ModItems.WORN_SHOVEL);

                    entries.accept(ModItems.RESTORED_PICKAXE);
                    entries.accept(ModItems.RESTORED_AXE);
                    entries.accept(ModItems.RESTORED_SHOVEL);

                    entries.accept(ModItems.PERFECTED_PICKAXE);
                    entries.accept(ModItems.PERFECTED_AXE);
                    entries.accept(ModItems.PERFECTED_SHOVEL);

                });
    }
}