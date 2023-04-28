package me.ranzeplay.mcee.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;

public class MCEEClient implements ClientModInitializer {
    public static final String MODID = "mcee";
    public static final Identifier CLIENT_CREATE_MARKER = new Identifier(MODID, "create_marker");

    @Override
    public void onInitializeClient() {
    }
}
