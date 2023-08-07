package org.pebbleprojects.testplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.pebbleprojects.testplugin.handler.Handler;

public final class TestPlugin extends JavaPlugin {

    public static TestPlugin INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        new Handler();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
