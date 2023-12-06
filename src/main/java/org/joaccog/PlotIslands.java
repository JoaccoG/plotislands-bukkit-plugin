package org.joaccog;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import org.joaccog.commands.MainCommand;
import org.joaccog.utils.LogUtils;
import org.joaccog.utils.ConfigurationHandler;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

public class PlotIslands extends JavaPlugin implements Listener {
    public static String prefix = "&8[&7PlotIslands&8] ";
    private final String pluginName = getDescription().getName();
    private final String pluginVersion = getDescription().getVersion();
    private Set<String> processedWorlds;
    private ConfigurationHandler configurationHandler;

    @Override
    public void onEnable() {
        try {
            LogUtils.info("Registering internal commands...");
            registerCommands();

            LogUtils.info("Getting plugin configuration...");
            configurationHandler = new ConfigurationHandler(this);
            processedWorlds = configurationHandler.getDatabase();

            LogUtils.info("Listening worlds creation events...");
            Bukkit
                .getPluginManager()
                .registerEvents(this, this);

            LogUtils.info("Successfully enabled on v" + pluginVersion);
        } catch (Exception e) {
            LogUtils.severe("Error while enabling " + pluginName + " v" + pluginVersion);
            LogUtils.severe("Error stack trace: \n" + e.getMessage());
            LogUtils.severe("Disabling...");
            onDisable();
        }
    }

    @Override
    public void onDisable() {
        configurationHandler.saveProcessedWorlds(processedWorlds);
        LogUtils.severe("Successfully disabled on v" + pluginVersion);
    }

    public void registerCommands() {
        Objects
            .requireNonNull(this.getCommand("plotislands"))
            .setExecutor(new MainCommand());
    }

    @EventHandler
    public void onWorldCreation(WorldInitEvent event) {
        String worldName = event.getWorld().getName();

        if (!processedWorlds.contains(worldName)) {
            String customCommand = "vt toggle " + worldName;

            Bukkit.getScheduler().runTaskLater(this, () -> {
                try {
                    LogUtils.info("Executing custom commands for world: " + worldName);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), customCommand);
                    LogUtils.info("Successfully executed all commands.");
                    LogUtils.info("Saving world on database");
                    if (configurationHandler.saveProcessedWorlds(processedWorlds)) {
                        LogUtils.info("Successfully saved world on database.");
                    } else {
                        LogUtils.severe("Error while saving database files");
                    }
                } catch (Exception e) {
                    LogUtils.severe("Unexpected error while executing commands on world: " + worldName);
                    LogUtils.severe("Error stack trace: \n" + e.getMessage());
                }
            }, 100L);
        } else {
            LogUtils.warning("Skipping commands execution on world: " + worldName);
        }
    }
}
