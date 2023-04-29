package me.ranzeplay.mcee.client.commands;

import com.google.gson.Gson;
import com.mojang.brigadier.context.CommandContext;
import me.ranzeplay.mcee.models.networking.Marker;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static me.ranzeplay.mcee.client.MCEEClient.waypointManager;

public class SyncCommand {
    public static int process(CommandContext<FabricClientCommandSource> context) {
        var player = MinecraftClient.getInstance().player;
        assert player != null;
        player.sendMessage(Text.literal("Syncing markers...").formatted(Formatting.YELLOW));

        waypointManager.sync();
        return 0;
    }

    public static void callback(MinecraftClient client, PacketByteBuf buf) {
        // Deserialize packet
        var json = buf.readString();
        var markers = new Gson().fromJson(json, Marker[].class);

        waypointManager.resetWaypoints(markers);

        assert client.player != null;
        client.player.sendMessage(Text.literal("Synced " + markers.length + " marker(s) successfully!").formatted(Formatting.GREEN));
    }
}
