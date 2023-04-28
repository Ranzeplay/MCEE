package me.ranzeplay.mcee.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

@DatabaseTable(tableName = "message")
public class Message {
    @DatabaseField(id = true)
    long id;

    @DatabaseField(canBeNull = false, foreign = true)
    CachedPlayer player;

    @DatabaseField(canBeNull = false)
    String message;

    @DatabaseField(canBeNull = false)
    Timestamp timestamp;

    public Message() {
    }

    public Message(long id, CachedPlayer player, String message, Timestamp timestamp) {
        this.id = id;
        this.player = player;
        this.message = message;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public CachedPlayer getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
