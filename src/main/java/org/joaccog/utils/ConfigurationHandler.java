package org.joaccog.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ConfigurationHandler {
    private final FileConfiguration config;
    private final File configFile;

    public ConfigurationHandler(Plugin plugin) {
        File pluginFolder = plugin.getDataFolder();
        this.configFile = new File(pluginFolder, "config.yml");
        File databaseFile = new File(pluginFolder, "database.yml");
        this.config = loadConfiguration(configFile);
    }

    private FileConfiguration loadConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            LogUtils.severe("Error while saving configuration files.");
            LogUtils.severe("Error stack trace: \n" + e.getMessage());
        }
    }

    public Set<String> getProcessedWorlds() {
        return new HashSet<>(config.getStringList("processedWorlds"));
    }

    public void saveProcessedWorlds(Set<String> processedWorlds) {
        config.set("processedWorlds", new LinkedList<>(processedWorlds));
        saveConfig();
    }
}
