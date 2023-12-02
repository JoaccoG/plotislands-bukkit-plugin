package org.joaccog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PlotIslands extends JavaPlugin {
    public static String prefix = "&8[&7PlotIslands&8]";
    private final String version = getDescription().getVersion();

    public void onEnable() {
        Bukkit
                .getConsoleSender()
                .sendMessage(
                        ChatColor.translateAlternateColorCodes(
                                '&', String.format("%s &eEnabled successfully (v%s)", prefix, version)
                        )
                );
    }
    public void onDisable() {
        Bukkit
                .getConsoleSender()
                .sendMessage(
                        ChatColor.translateAlternateColorCodes(
                                '&', String.format("%s &eDisabled successfully (v%s)", prefix, version)
                        )
                );
    }

}
