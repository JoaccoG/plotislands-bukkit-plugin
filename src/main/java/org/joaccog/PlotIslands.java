package org.joaccog;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.joaccog.commands.MainCommand;
import org.joaccog.utils.LogUtils;
import org.joaccog.utils.ConfigurationHandler;
import org.joaccog.world.WorldCreationHandler;

import java.util.Objects;
import java.util.Set;

public class PlotIslands extends JavaPlugin implements Listener {
    public static String prefix = "[PlotIslands] ";
    public static String formattedPrefix = "&8[&7PlotIslands&8] ";
    private final String pluginVersion = getDescription().getVersion();
    private Set<String> processedWorlds;

    // Instances
    private ConfigurationHandler configurationHandler;
    private WorldCreationHandler worldCreationHandler;

    @Override
    public void onEnable() {
        try {
            LogUtils.info("Initializing...");
            registerCommands();
            worldCreationHandler = new WorldCreationHandler(this);
            Bukkit
                .getPluginManager()
                .registerEvents(worldCreationHandler, this);

            LogUtils.info("Successfully enabled on v" + pluginVersion);
        } catch (Exception e) {
            LogUtils.severe("Error while enabling on v" + pluginVersion);
            LogUtils.severe("Error stack trace: \n" + e.getMessage());
            LogUtils.severe("Disabling...");
            onDisable();
        }
    }

    @Override
    public void onDisable() {
        configurationHandler.saveProcessedWorlds(processedWorlds);
        LogUtils.info("Successfully disabled on v" + pluginVersion);
    }

    public void registerCommands() {
        Objects
            .requireNonNull(this.getCommand("plotislands"))
            .setExecutor(new MainCommand());
    }

    @EventHandler
    public void onWorldCreation(WorldInitEvent event) {
        worldCreationHandler.handleWorldCreation(event);
    }
}
