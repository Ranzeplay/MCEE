package me.ranzeplay.mcee.models.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import me.ranzeplay.mcee.models.networking.Marker;

import java.sql.Timestamp;

@DatabaseTable(tableName = "markers")
public class DbMarker {
    @DatabaseField(id = true)
    long id;

    @DatabaseField(canBeNull = false)
    int x;
    @DatabaseField(canBeNull = false)
    int y;
    @DatabaseField(canBeNull = false)
    int z;
    @DatabaseField(canBeNull = false)
    String dimension;

    @DatabaseField(canBeNull = false, foreign = true)
    DbPlayer author;

    @DatabaseField(canBeNull = false)
    String name;

    @DatabaseField(canBeNull = false)
    Timestamp timestamp;

    @DatabaseField(canBeNull = false)
    int color;

    public DbMarker() {
    }

    public DbMarker(long id, DbPlayer player, Timestamp timestamp, Marker networkingMarker) {
        this.id = id;

        this.author = player;
        this.timestamp = timestamp;
        this.name = networkingMarker.getName();

        this.x = networkingMarker.getPosition().getX();
        this.y = networkingMarker.getPosition().getY();
        this.z = networkingMarker.getPosition().getZ();
        this.dimension = networkingMarker.getDimension();
        this.color = networkingMarker.getColor();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public DbPlayer getAuthor() {
        return author;
    }

    public void setAuthor(DbPlayer author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
