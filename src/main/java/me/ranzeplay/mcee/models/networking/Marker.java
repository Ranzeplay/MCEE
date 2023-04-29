package me.ranzeplay.mcee.models.networking;

import journeymap.client.api.display.Waypoint;
import net.minecraft.util.math.Vec3i;

import java.util.UUID;

public class Marker {
    long id;
    Vec3i position;
    String dimension;
    String name;
    int color;

    public Marker(long id, Vec3i position, String dimension, String name, int color) {
        this.id = id;
        this.position = position;
        this.dimension = dimension;
        this.name = name;
        this.color = color;
    }

    public Marker(Waypoint waypoint) {
        position = waypoint.getPosition();
        dimension = waypoint.getDimension();
        name = waypoint.getName();
    }

    public Vec3i getPosition() {
        return position;
    }

    public void setPosition(Vec3i position) {
        this.position = position;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
