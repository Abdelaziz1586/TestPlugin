package org.pebbleprojects.testplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pebbleprojects.testplugin.session.Session;
import org.pebbleprojects.testplugin.session.SessionHandler;

public class Register implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        new Thread(() -> {
            if (sender instanceof Player) {
                final Player player = (Player) sender;

                final Session session = SessionHandler.INSTANCE.getSession(player.getUniqueId());

                if (session != null) {
                    if (session.password != null) {
                        player.sendMessage("/login <password>");
                        return;
                    }

                    session.update(args.length > 0 ? args[0] : null);
                    return;
                }

                player.sendMessage("You're not in a session");
            }
        }).start();
        return false;
    }
}
