package me.ranzeplay.mcee.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class CommandHandler {
    public static void registerCommand(CommandDispatcher<FabricClientCommandSource> dispatcher){
        dispatcher.register(ClientCommandManager.literal("mcee")
                .then(ClientCommandManager.literal("sync")
                        .executes(SyncCommand::process)));
    }
}
