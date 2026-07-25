package com.corescode.legacytools;

import com.corescode.legacytools.item.ModItemGroups;
import com.corescode.legacytools.item.ModItems;
import net.fabricmc.api.ModInitializer;

import com.corescode.legacytools.component.ModDataComponents;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.corescode.legacytools.progression.MiningProgressHandler;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class LegacyTools implements ModInitializer {
	public static final String MOD_ID = "legacytools";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Legacy Tools...");

		ModDataComponents.register();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		PlayerBlockBreakEvents.AFTER.register((level, player, pos, state, blockEntity) -> {
			MiningProgressHandler.handle(player, state);
		});

		LOGGER.info("Legacy Tools initialized successfully.");
	}


	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
