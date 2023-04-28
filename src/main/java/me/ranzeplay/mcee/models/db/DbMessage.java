package me.ranzeplay.mcee.models.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

@DatabaseTable(tableName = "message")
public class DbMessage {
    @DatabaseField(id = true)
    long id;

    @DatabaseField(canBeNull = false, foreign = true)
    DbPlayer player;

    @DatabaseField(canBeNull = false)
    String message;

    @DatabaseField(canBeNull = false)
    Timestamp timestamp;

    public DbMessage() {
    }

    public DbMessage(long id, DbPlayer player, String message, Timestamp timestamp) {
        this.id = id;
        this.player = player;
        this.message = message;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public DbPlayer getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
