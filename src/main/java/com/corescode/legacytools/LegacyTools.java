package com.corescode.legacytools;

import com.corescode.legacytools.component.ModDataComponents;
import com.corescode.legacytools.item.ModItemGroups;
import com.corescode.legacytools.item.ModItems;
import com.corescode.legacytools.progression.AxeProgressHandler;
import com.corescode.legacytools.progression.MiningProgressHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyTools implements ModInitializer {

	public static final String MOD_ID = "legacytools";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Initializing Legacy Tools...");

		ModDataComponents.register();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		PlayerBlockBreakEvents.AFTER.register((level, player, pos, state, blockEntity) -> {

			MiningProgressHandler.handle(player, state);
			AxeProgressHandler.handle(player, state);

		});

		LOGGER.info("Legacy Tools initialized successfully.");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}