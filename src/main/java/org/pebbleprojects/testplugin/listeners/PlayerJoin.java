package org.pebbleprojects.testplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.pebbleprojects.testplugin.session.SessionHandler;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        SessionHandler.INSTANCE.createSession(event.getPlayer());
    }

}
