package org.joaccog;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import org.joaccog.commands.MainCommand;
import org.joaccog.utils.MessageUtils;

public class PlotIslands extends JavaPlugin implements Listener {
    public static String prefix = "&8[&7PlotIslands&8] ";
    private final String pluginName = getDescription().getName();
    private final String pluginVersion = getDescription().getVersion();
    private final Set<String> processedWorlds = new HashSet<>();
    public static File dataFolder;
    public static File databaseFile;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        try {
            getLogger().info("Attempting to get plugin's folder");
            dataFolder = getDataFolder();
            databaseFile = new File(dataFolder, "database.yml");
            config = YamlConfiguration.loadConfiguration(databaseFile);
            processedWorlds.addAll(config.getStringList("processedWorlds"));
        } catch (Exception e) {
            getLogger().severe("Error while getting plugin's folder");
            getLogger().severe("Disabling " + pluginName);
            onDisable();
        }

        Bukkit.getPluginManager().registerEvents(this, this);

        registerCommands();
        Bukkit
            .getConsoleSender()
            .sendMessage(MessageUtils.getColoredMessageWithPrefix("&eEnabled successfully v" + pluginVersion));
    }

    @Override
    public void onDisable() {
        config.set("processedWorlds", new LinkedList<>(processedWorlds));
        saveConfig();
        Bukkit
            .getConsoleSender()
            .sendMessage(MessageUtils.getColoredMessageWithPrefix("&eDisabled successfully v" + pluginVersion));
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
            getLogger().info("Command stored, will try to execute it on: " + worldName);

            Bukkit.getScheduler().runTaskLater(this, () -> {
                try {
                    getLogger().info("Adding world: " + worldName + " to database");
                    processedWorlds.add(worldName);
                    getLogger().info("Executing custom commands for world: " + worldName);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), customCommand);
                    getLogger().info("VT+ toggled for world: " + worldName);
                } catch (Exception e) {
                    e.printStackTrace();
                    getLogger().severe("Error toggling VT+ for world: " + worldName);
                }
            }, 100L);
        } else {
            getLogger().info("Skipping ");
        }
    }
}
