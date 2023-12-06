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
    private final String version = getDescription().getVersion();
    private Set<String> processedWorlds = new HashSet<>();
    private File dataFolder;
    private File databaseFile;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        dataFolder = getDataFolder();
        databaseFile = new File(dataFolder, "database.yml");
        config = YamlConfiguration.loadConfiguration(databaseFile);
        processedWorlds.addAll(config.getStringList("processedWorlds"));

        Bukkit.getPluginManager().registerEvents(this, this);

        registerCommands();
        Bukkit
            .getConsoleSender()
            .sendMessage(MessageUtils.getColoredMessageWithPrefix("&eEnabled successfully v" + version));
    }

    @Override
    public void onDisable() {
        config.set("processedWorlds", new LinkedList<>(processedWorlds));
        saveConfig();
        Bukkit
            .getConsoleSender()
            .sendMessage(MessageUtils.getColoredMessageWithPrefix("&eDisabled successfully v" + version));
    }

    public void registerCommands() {
        Objects.requireNonNull(this.getCommand("plotislands"))
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
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), customCommand);
                    processedWorlds.add(worldName);
                    getLogger().info("VT+ toggled for world: " + worldName);
                } catch (Exception e) {
                    e.printStackTrace();
                    getLogger().severe("Error toggling VT+ for world: " + worldName);
                }
            }, 100L);
        }
    }
}
