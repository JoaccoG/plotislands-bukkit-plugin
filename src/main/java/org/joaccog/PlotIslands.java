package org.joaccog;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.joaccog.commands.MainCommand;
import org.joaccog.utils.MessageUtils;

public class PlotIslands extends JavaPlugin {
    public static String prefix = "&8[&7PlotIslands&8] ";
    private final String version = getDescription().getVersion();

    public void onEnable() {
        registerCommands();

        Bukkit
            .getConsoleSender()
            .sendMessage(MessageUtils.getColoredMessage("&eEnabled successfully v" + version));
    }

    public void onDisable() {
        Bukkit
            .getConsoleSender()
            .sendMessage(MessageUtils.getColoredMessage("&eDisabled successfully v" + version));
    }

    public void registerCommands() {
        this.getCommand("plotislands").setExecutor(new MainCommand());
    }

}
