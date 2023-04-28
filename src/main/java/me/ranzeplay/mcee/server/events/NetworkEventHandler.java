package me.ranzeplay.mcee.server.events;

import com.google.gson.Gson;
import me.ranzeplay.mcee.server.MCEEServer;
import me.ranzeplay.mcee.models.db.DbMarker;
import me.ranzeplay.mcee.models.networking.Marker;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

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
}