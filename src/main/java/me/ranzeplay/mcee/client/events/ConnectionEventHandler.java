package me.ranzeplay.mcee.client.events;

import me.ranzeplay.mcee.client.MCEEClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ConnectionEventHandler {
    public static void disconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        MCEEClient.waypointManager.removeAllWaypoints();
    }
}
