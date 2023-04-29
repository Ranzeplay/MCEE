package me.ranzeplay.mcee.client.integration.journeymap;

import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.event.ClientEvent;
import journeymap.client.api.event.WaypointEvent;
import me.ranzeplay.mcee.client.MCEEClient;
import me.ranzeplay.mcee.client.events.WaypointEventHandler;
import me.ranzeplay.mcee.client.manager.WaypointManager;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

import static journeymap.client.api.event.ClientEvent.Type.*;

public class JourneyMapPlugin implements IClientPlugin {
    public IClientAPI jmAPI = null;
    public static JourneyMapPlugin Instance;

    public JourneyMapPlugin() {
        Instance = this;
    }

    @Override
    public void initialize(final @NotNull IClientAPI jmClientApi) {
        jmAPI = jmClientApi;
        MCEEClient.waypointManager = new WaypointManager(jmClientApi);

        this.jmAPI.subscribe(getModId(), EnumSet.of(WAYPOINT, DEATH_WAYPOINT));
    }

    @Override
    public String getModId() {
        return MCEEClient.MODID;
    }

    @Override
    public void onEvent(ClientEvent event) {
        if(event.type == WAYPOINT) {
            try {
                WaypointEventHandler.process((WaypointEvent) event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
