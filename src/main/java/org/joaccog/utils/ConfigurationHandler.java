package org.joaccog.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ConfigurationHandler {
    private final File configFile;
    private final File databaseFile;
    private FileConfiguration configConfig;
    private FileConfiguration databaseConfig;

    public ConfigurationHandler(Plugin plugin) {
        File pluginFolder = plugin.getDataFolder();
        this.configFile = new File(pluginFolder, "config.yml");
        this.databaseFile = new File(pluginFolder, "database.yml");

        File[] filesArray = new File[]{configFile, databaseFile};
        FileConfiguration(filesArray);
    }

    private void FileConfiguration(File[] files) {
        configConfig = YamlConfiguration.loadConfiguration(files[0]);
        databaseConfig = YamlConfiguration.loadConfiguration(files[1]);
    }

    private boolean saveAllFiles() {
        try {
            configConfig.save(configFile);
            databaseConfig.save(databaseFile);
            return true;
        } catch (Exception e) {
            LogUtils.severe("Error while saving files.");
            LogUtils.severe("Error stack trace: \n" + e.getMessage());
            return false;
        }
    }

    public FileConfiguration getConfig() {
        return configConfig;
    }

    public Set<String> getDatabase() {
        return new HashSet<>(databaseConfig.getStringList("processedWorlds"));
    }

    public boolean saveProcessedWorlds(Set<String> processedWorlds) {
        databaseConfig.set("processedWorlds", new LinkedList<>(processedWorlds));
        return saveAllFiles();
    }
}
