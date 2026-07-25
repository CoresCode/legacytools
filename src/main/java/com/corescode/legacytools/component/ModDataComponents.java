package com.corescode.legacytools.component;

import com.corescode.legacytools.LegacyTools;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModDataComponents {

    public static final DataComponentType<LegacyToolData> LEGACY_DATA =
            Registry.register(
                    BuiltInRegistries.DATA_COMPONENT_TYPE,
                    LegacyTools.id("legacy_data"),
                    DataComponentType.<LegacyToolData>builder()
                            .persistent(LegacyToolData.CODEC)
                            .build()
            );

    private ModDataComponents() {
    }

    public static void register() {
        LegacyTools.LOGGER.info("Registering Data Components...");
    }
}