package me.ranzeplay.mcee.server.events;

import me.ranzeplay.mcee.server.MCEEServer;
import me.ranzeplay.mcee.models.db.DbPlayer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import java.sql.SQLException;

public class PlayerJoinEventHandler {
    public static void process(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        var playerDao = MCEEServer.configManager.getDbManager().getPlayerDao();

        var player = handler.getPlayer();

        // Create if not exists
        try {
            if(playerDao.queryForId(player.getUuid()) == null) {
                playerDao.create(new DbPlayer(player.getUuid(), player.getName().getString()));
            }
        } catch (SQLException e) {
            MCEEServer.LOGGER.error("Error creating player record for " + player.getName().getString());
            throw new RuntimeException(e);
        }
    }
}
