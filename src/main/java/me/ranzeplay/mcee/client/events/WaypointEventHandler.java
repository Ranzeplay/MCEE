package me.ranzeplay.mcee.client.events;

import com.google.gson.Gson;
import journeymap.client.api.display.Waypoint;
import journeymap.client.api.event.WaypointEvent;
import me.ranzeplay.mcee.client.MCEEClient;
import me.ranzeplay.mcee.models.networking.Marker;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

public class WaypointEventHandler {
    public static void process(WaypointEvent event) {
        switch (event.getContext()) {
            case CREATE -> {
                createWaypoint(event.getWaypoint());
            }
            default -> {
            }
        }
    }

    private static void createWaypoint(Waypoint waypoint) {
        var transmissionModel = new Marker(waypoint);
        var str = new Gson().toJson(transmissionModel);

        ClientPlayNetworking.send(MCEEClient.CLIENT_CREATE_MARKER, PacketByteBufs.create().writeString(str));
    }
}
