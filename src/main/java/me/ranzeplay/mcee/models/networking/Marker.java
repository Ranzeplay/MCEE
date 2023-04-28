package me.ranzeplay.mcee.models.networking;

import journeymap.client.api.display.Waypoint;
import net.minecraft.util.math.Vec3d;

public class Marker {
    Vec3d position;
    String dimension;
    String name;

    public Marker(Vec3d position, String dimension, String name) {
        this.position = position;
        this.dimension = dimension;
        this.name = name;
    }

    public Marker(Waypoint waypoint) {
        position = waypoint.getPosition().toCenterPos();
        dimension = waypoint.getDimension();
        name = waypoint.getName();
    }

    public Vec3d getPosition() {
        return position;
    }

    public void setPosition(Vec3d position) {
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
}
