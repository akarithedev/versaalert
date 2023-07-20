package dev.ayame.versaalert;

import org.bukkit.plugin.java.JavaPlugin;
import dev.ayame.versaalert.handlers.dataHandler;
import dev.ayame.versaalert.events.*;
import dev.ayame.versaalert.commands.*;

import java.util.Objects;

public final class VersaAlert extends JavaPlugin {
    public static VersaAlert INSTANCE;
    @Override
    public void onEnable() {
        INSTANCE = this;
        new dataHandler();
        getServer().getPluginManager().registerEvents(new PlayerPickupItem(), this);
        Objects.requireNonNull(getCommand("versaalert")).setExecutor(new MainCommand());
        getLogger().info("Loading config...");
        getLogger().info("Config loaded");
        getLogger().info("The plugin is now enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Unloading commands and config");
        getLogger().info("Plugin is now disabled");
    }
}

