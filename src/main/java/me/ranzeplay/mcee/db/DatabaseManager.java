package me.ranzeplay.mcee.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.ranzeplay.mcee.models.CachedPlayer;
import me.ranzeplay.mcee.models.Message;
import net.minecraft.client.gui.tab.Tab;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseManager {
    final String connectionString;
    ConnectionSource connectionSource;

    // Dao
    Dao<CachedPlayer, UUID> playerDao;
    Dao<Message, Long> messageDao;

    public DatabaseManager(Path dbFile) throws SQLException {
        connectionString = "jdbc:sqlite:" + dbFile;
        connectionSource = new JdbcConnectionSource(connectionString);

        playerDao = DaoManager.createDao(connectionSource, CachedPlayer.class);
        messageDao = DaoManager.createDao(connectionSource, Message.class);
    }

    public void tryInit() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, CachedPlayer.class);
        TableUtils.createTableIfNotExists(connectionSource, Message.class);
    }

    public Dao<CachedPlayer, UUID> getPlayerDao() {
        return playerDao;
    }

    public Dao<Message, Long> getMessageDao() {
        return messageDao;
    }
}
