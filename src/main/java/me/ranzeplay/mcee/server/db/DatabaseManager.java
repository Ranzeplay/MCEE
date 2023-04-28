package me.ranzeplay.mcee.server.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.ranzeplay.mcee.models.db.DbMarker;
import me.ranzeplay.mcee.models.db.DbMessage;
import me.ranzeplay.mcee.models.db.DbPlayer;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseManager {
    final String connectionString;
    ConnectionSource connectionSource;

    // Dao
    Dao<DbPlayer, UUID> playerDao;
    Dao<DbMessage, Long> messageDao;
    Dao<DbMarker, Long> markerDao;

    public DatabaseManager(Path dbFile) throws SQLException {
        connectionString = "jdbc:sqlite:" + dbFile;
        connectionSource = new JdbcConnectionSource(connectionString);

        playerDao = DaoManager.createDao(connectionSource, DbPlayer.class);
        messageDao = DaoManager.createDao(connectionSource, DbMessage.class);
        markerDao = DaoManager.createDao(connectionSource, DbMarker.class);
    }

    public void tryInit() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, DbPlayer.class);
        TableUtils.createTableIfNotExists(connectionSource, DbMessage.class);
        TableUtils.createTableIfNotExists(connectionSource, DbMarker.class);
    }

    public Dao<DbPlayer, UUID> getPlayerDao() {
        return playerDao;
    }

    public Dao<DbMessage, Long> getMessageDao() {
        return messageDao;
    }

    public Dao<DbMarker, Long> getMarkerDao() {
        return markerDao;
    }
}
