package me.ranzeplay.mcee.client;

import me.ranzeplay.mcee.client.commands.CommandHandler;
import me.ranzeplay.mcee.client.commands.SyncCommand;
import me.ranzeplay.mcee.client.events.ConnectionEventHandler;
import me.ranzeplay.mcee.client.manager.WaypointManager;
import me.ranzeplay.mcee.server.MCEEServer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class MCEEClient implements ClientModInitializer {
    public static final String MODID = "mcee";

    public static final Identifier CLIENT_CREATE_MARKER = new Identifier(MODID, "client_create_marker");
    public static final Identifier CLIENT_PULL_MARKERS = new Identifier(MODID, "client_pull_markers");

    public static WaypointManager waypointManager;

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(MCEEServer.SERVER_PUSH_MARKERS, (minecraftClient, clientPlayNetworkHandler, packetByteBuf, packetSender)
                -> SyncCommand.callback(minecraftClient, packetByteBuf));

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> CommandHandler.registerCommand(dispatcher));
        ClientPlayConnectionEvents.DISCONNECT.register(ConnectionEventHandler::disconnect);
    }
}
