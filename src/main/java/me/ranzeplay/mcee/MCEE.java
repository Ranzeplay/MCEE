package me.ranzeplay.mcee;

import me.ranzeplay.mcee.config.ConfigManager;
import me.ranzeplay.mcee.events.PlayerJoinEventHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class MCEE implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public static ConfigManager configManager;

    @Override
    public void onInitialize() {
        LOGGER.debug("Initializing MCEE");

        try {
            configManager = new ConfigManager();

            configManager.initializeDatabase();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        ServerPlayConnectionEvents.JOIN.register(PlayerJoinEventHandler::process);
    }
}
