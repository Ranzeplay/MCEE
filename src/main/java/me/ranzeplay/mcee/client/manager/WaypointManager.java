package me.ranzeplay.mcee.client.manager;

import com.google.gson.Gson;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.display.Waypoint;
import journeymap.client.api.display.WaypointGroup;
import me.ranzeplay.mcee.client.MCEEClient;
import me.ranzeplay.mcee.models.networking.Marker;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.UUID;

public class WaypointManager {
    private final IClientAPI jmApi;
    private final WaypointGroup waypointGroup;
    private final ArrayList<Waypoint> pendingWaypoints;
    private final ArrayList<Waypoint> showingWaypoints;

    public WaypointManager(IClientAPI jmApi) {
        this.jmApi = jmApi;
        this.waypointGroup = new WaypointGroup(MCEEClient.MODID, "MCEE Server Sync");
        pendingWaypoints = new ArrayList<>();
        showingWaypoints = new ArrayList<>();
    }

    public void requestPull() {
        ClientPlayNetworking.send(MCEEClient.CLIENT_PULL_MARKERS, PacketByteBufs.empty());
    }

    public void sync() {
        pushAllTaggedWaypoints();
        requestPull();
    }

    public void resetWaypoints(Marker[] markers) {
        removeAllWaypoints();

        for (var marker : markers) {
            var waypoint = new Waypoint(MCEEClient.MODID, formatId(marker.getId()), marker.getName(), marker.getDimension(), new BlockPos(marker.getPosition()));
            waypoint.setGroup(waypointGroup);
            showingWaypoints.add(waypoint);

            try {
                jmApi.show(waypoint);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeAllWaypoints() {
        for(var w : showingWaypoints) {
            jmApi.remove(w);
        }
    }

    public void pushAllTaggedWaypoints() {
        pushWaypoints(pendingWaypoints);

        // Remove waypoints synced, because the server has synced to us the updated list.
        for (Waypoint waypoint : pendingWaypoints) {
            jmApi.remove(waypoint);
        }

        pendingWaypoints.clear();
    }

    public void pushWaypoints(ArrayList<Waypoint> waypoints) {
        for (Waypoint waypoint : waypoints) {
            pushWaypoint(waypoint);
        }
    }

    public void pushWaypoint(Waypoint waypoint) {
        var transmissionModel = new Marker(waypoint);
        var str = new Gson().toJson(transmissionModel);

        ClientPlayNetworking.send(MCEEClient.CLIENT_CREATE_MARKER, PacketByteBufs.create().writeString(str));
    }

    public String formatId(long dbId) {
        return "mcee-wpt-" + dbId;
    }

    public WaypointGroup getWaypointGroup() {
        return waypointGroup;
    }

    public Waypoint queueWaypoint(Waypoint originalWaypoint) {
        var name = originalWaypoint.getName().replace("[sync]", "").trim();

        var waypoint = new Waypoint(MCEEClient.MODID, UUID.randomUUID().toString(), name, originalWaypoint.getDimension(), originalWaypoint.getPosition());
        waypoint.setGroup(waypointGroup);
        waypoint.setColor(originalWaypoint.getColor());

        return waypoint;
    }
}
