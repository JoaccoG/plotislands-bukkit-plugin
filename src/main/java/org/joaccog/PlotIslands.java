package org.joaccog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PlotIslands extends JavaPlugin {

    public void onEnable() {
        Bukkit
                .getConsoleSender()
                .sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&6Enabling PlotIslands v...")
                );
    }

    public void onDisable() {
        Bukkit
                .getConsoleSender()
                .sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&6Disabling PlotIslands v...")
                );
    }

}
