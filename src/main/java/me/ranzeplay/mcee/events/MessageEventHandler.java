package me.ranzeplay.mcee.events;

import me.ranzeplay.mcee.MCEE;
import me.ranzeplay.mcee.models.db.DbMessage;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class MessageEventHandler {
    public static void processChat(SignedMessage message, ServerPlayerEntity player, MessageType.Parameters parameters) {
        try {
            var cachedPlayer = MCEE.configManager.getDbManager().getPlayerDao().queryForId(player.getUuid());

            var id = MCEE.configManager.getDbManager().getMessageDao().countOf();
            var messageToSave = new DbMessage(id, cachedPlayer, message.getContent().getString(), Timestamp.from(Instant.now()));
            MCEE.configManager.getDbManager().getMessageDao().create(messageToSave);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
