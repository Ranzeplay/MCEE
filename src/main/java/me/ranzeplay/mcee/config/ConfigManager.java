package me.ranzeplay.mcee.config;

import me.ranzeplay.mcee.db.DatabaseManager;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class ConfigManager {
    Path configDirectory;
    DatabaseManager dbManager;

    public ConfigManager() throws SQLException, IOException {
        configDirectory = Paths.get(FabricLoader.getInstance().getConfigDir().toAbsolutePath().toString(), "mcee");
        if(!configDirectory.toFile().exists()) {
            configDirectory.toFile().mkdirs();
        };

        Path dbFilePath = Paths.get(configDirectory.toAbsolutePath().toString(), "data.db");
        dbManager = new DatabaseManager(dbFilePath);
    }

    public void initializeDatabase() throws SQLException {
        dbManager.tryInit();
    }

    public DatabaseManager getDbManager() {
        return dbManager;
    }
}
