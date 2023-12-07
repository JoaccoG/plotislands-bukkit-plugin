package org.joaccog.world;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.Plugin;
import org.joaccog.utils.ConfigurationHandler;
import org.joaccog.utils.LogUtils;

import java.util.List;
import java.util.Set;

public class WorldCreationHandler implements Listener {
    private final Plugin plugin;
    private final ConfigurationHandler configurationHandler;
    private final Set<String> processedWorlds;

    public WorldCreationHandler(Plugin plugin) {
        this.plugin = plugin;
        this.configurationHandler = new ConfigurationHandler(plugin);
        processedWorlds = configurationHandler.getDatabase();
    }

    @EventHandler
    public void handleWorldCreation(WorldInitEvent event) {
        String worldName = event.getWorld().getName();

        if (!processedWorlds.contains(worldName)) {
            List<String> customCommands = configurationHandler.getConfig().getStringList("customCommands");

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                try {
                    LogUtils.info("Executing custom commands for world: " + worldName);
                    for (String customCommand : customCommands) {
                        String formattedCommand = customCommand.replace("%world%", worldName);
                        LogUtils.info("Executing '" + formattedCommand + "'...");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), formattedCommand);
                    }

                    LogUtils.info("Saving world on database");
                    processedWorlds.add(worldName);
                    configurationHandler.saveProcessedWorlds(processedWorlds);

                    LogUtils.info("Success!");
                } catch (Exception e) {
                    LogUtils.severe("Unexpected error while executing commands on world: " + worldName);
                    LogUtils.severe("Error stack trace: \n" + e.getMessage());
                }
            }, 100L);
        } else {
            LogUtils.warning("Skipping world: " + worldName);
        }
    }

    public void saveWorldsData() {
        configurationHandler.saveProcessedWorlds(processedWorlds);
    }
}
