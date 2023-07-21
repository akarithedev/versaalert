package dev.ayame.versaalert.handlers;

import dev.ayame.versaalert.VersaAlert;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Objects;

import org.bukkit.scheduler.BukkitRunnable;

public class dataHandler {
    public static dataHandler INSTANCE;
    private FileConfiguration config;
    public dataHandler() {
        INSTANCE = this;
        VersaAlert.INSTANCE.getConfig().options().copyDefaults(true);
        VersaAlert.INSTANCE.saveDefaultConfig();
        updateConfig();

    }
    public void updateConfig() {
        VersaAlert.INSTANCE.reloadConfig();
        config = VersaAlert.INSTANCE.getConfig();
    }

    public Object getConfig(final String key, final boolean translate) {
        return config.isSet(key) ? (translate ? ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString(key))).replace("%nl%", "\n") : config.get(key)) : null;
    }
    public void runTask(final Runnable runnable) {
        if (!Bukkit.isPrimaryThread()) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    runnable.run();
                }
            }.runTask(VersaAlert.INSTANCE);
            return;
        }
        runnable.run();
    }

}


