package org.pebbleprojects.testplugin.session;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SessionHandler {

    public static SessionHandler INSTANCE;
    private final HashMap<UUID, Session> sessions;

    public SessionHandler() {
        INSTANCE = this;

        sessions = new HashMap<>();
    }

    public void createSession(final Player player) {
        sessions.put(player.getUniqueId(), new Session(player));
    }

    public void removeSession(final UUID uuid) {
        sessions.remove(uuid);
    }

    public final Session getSession(final UUID uuid) {
        return sessions.getOrDefault(uuid, null);
    }
}
