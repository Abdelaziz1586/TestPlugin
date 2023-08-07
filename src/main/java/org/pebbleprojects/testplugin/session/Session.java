package org.pebbleprojects.testplugin.session;

import org.bukkit.entity.Player;
import org.pebbleprojects.testplugin.handler.Handler;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Session {

    private final Player player;
    public final Object password;

    public Session(final Player player) {
        this.player = player;

        password = Handler.INSTANCE.getData(player.getUniqueId().toString());

        player.sendMessage("Please " + (password == null ? "register using /register" : "login using /login"));
    }

    public void update(final String password) {
        if (password == null) {
            player.sendMessage("Please write the full command");
            return;
        }

        if (this.password == null) {
            Handler.INSTANCE.writeData(player.getUniqueId().toString(), BCrypt.hashpw(password, BCrypt.gensalt(12)));

            SessionHandler.INSTANCE.removeSession(player.getUniqueId());

            player.sendMessage("Successfully registered!");
            return;
        }

        if (BCrypt.checkpw(password, this.password.toString())) {
            SessionHandler.INSTANCE.removeSession(player.getUniqueId());

            player.sendMessage("Successfully logged in!");
            return;
        }
        player.sendMessage("Incorrect password");
    }

}
