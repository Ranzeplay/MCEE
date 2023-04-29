package me.ranzeplay.mcee.client.events;

import journeymap.client.api.display.Waypoint;
import journeymap.client.api.event.WaypointEvent;
import me.ranzeplay.mcee.client.MCEEClient;
import me.ranzeplay.mcee.client.integration.journeymap.JourneyMapPlugin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static me.ranzeplay.mcee.client.MCEEClient.waypointManager;

public class WaypointEventHandler {
    public static void process(WaypointEvent event) throws Exception {
        switch (event.getContext()) {
            case CREATE -> {
                if (createWaypoint(event.getWaypoint())) {
                    event.cancel();
                }
            }
            default -> {
            }
        }
    }

    private static boolean createWaypoint(Waypoint waypoint) throws Exception {
        if (waypoint.getName().startsWith("[sync]")) {
            var queuedWaypoint = waypointManager.queueWaypoint(waypoint);
            JourneyMapPlugin.Instance.jmAPI.show(queuedWaypoint);

            var client = MinecraftClient.getInstance();
            assert client.player != null;
            client.player.sendMessage(Text.literal("Created a waypoint to sync").formatted(Formatting.YELLOW));

            return true;
        }

        return false;
    }
}
