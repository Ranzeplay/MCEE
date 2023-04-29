package me.ranzeplay.mcee.server.events;

import com.google.gson.Gson;
import me.ranzeplay.mcee.server.MCEEServer;
import me.ranzeplay.mcee.models.db.DbMarker;
import me.ranzeplay.mcee.models.networking.Marker;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3i;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

public class NetworkEventHandler {

    public static void processMarkerCreation(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf) {
        var markerDao = MCEEServer.configManager.getDbManager().getMarkerDao();

        try {
            var dbPlayer = MCEEServer.configManager.getDbManager().getPlayerDao().queryForId(player.getUuid());

            var originalModel = new Gson().fromJson(buf.readString(), Marker.class);
            var dbModel = new DbMarker(markerDao.countOf(), dbPlayer, Timestamp.from(Instant.now()), originalModel);

            markerDao.create(dbModel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void processMarkerSync(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf) {
        var markerDao = MCEEServer.configManager.getDbManager().getMarkerDao();

        try {
            var markers = markerDao.queryForAll();
            var transMarkers = new ArrayList<Marker>();
            for(var marker : markers) {
                transMarkers.add(new Marker(marker.getId(), new Vec3i(marker.getX(), marker.getY(), marker.getZ()), marker.getDimension(), marker.getName(), marker.getColor()));
            }

            var json = new Gson().toJson(transMarkers.toArray());

            ServerPlayNetworking.send(player, MCEEServer.SERVER_PUSH_MARKERS, PacketByteBufs.create().writeString(json));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
