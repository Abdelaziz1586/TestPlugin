package org.pebbleprojects.testplugin.handler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.pebbleprojects.testplugin.TestPlugin;
import org.pebbleprojects.testplugin.commands.Login;
import org.pebbleprojects.testplugin.commands.Register;
import org.pebbleprojects.testplugin.listeners.PlayerJoin;
import org.pebbleprojects.testplugin.session.SessionHandler;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Handler {

    private final File dataFile;
    private FileConfiguration data;
    public static Handler INSTANCE;

    public Handler() {
        INSTANCE = this;

        if (TestPlugin.INSTANCE.getDataFolder().mkdirs()) {
            TestPlugin.INSTANCE.getLogger().info("Created plugin folder");
        }

        dataFile = new File(TestPlugin.INSTANCE.getDataFolder().getPath(), "data.yml");

        if (!dataFile.exists()) {
            try {
                if (dataFile.createNewFile())
                    TestPlugin.INSTANCE.getServer().getConsoleSender().sendMessage("§aCreated data.yml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updateData();

        new SessionHandler();

        TestPlugin.INSTANCE.getServer().getPluginManager().registerEvents(new PlayerJoin(), TestPlugin.INSTANCE);

        Objects.requireNonNull(TestPlugin.INSTANCE.getCommand("register")).setExecutor(new Register());
        Objects.requireNonNull(TestPlugin.INSTANCE.getCommand("login")).setExecutor(new Login());
    }

    public void updateData() {
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void writeData(final String key, final Object value) {
        data.set(key, value);
        try {
            data.save(dataFile);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public Object getData(final String key) {
        return data.isSet(key) ? data.get(key) : null;
    }

}
