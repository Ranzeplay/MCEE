package me.ranzeplay.mcee.models.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

@DatabaseTable(tableName = "players")
public class DbPlayer {
    @DatabaseField(id = true, canBeNull = false)
    UUID uuid;

    @DatabaseField(canBeNull = false)
    String name;

    public DbPlayer() {
    }

    public DbPlayer(UUID uuid, String name) {
        this.name = name;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
