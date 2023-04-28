package me.ranzeplay.mcee;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCEE implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    @Override
    public void onInitialize() {
        LOGGER.debug("Initializing MCEE");
    }
}
