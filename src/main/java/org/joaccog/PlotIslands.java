package org.joaccog;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.joaccog.commands.MainCommand;
import org.joaccog.utils.MessageUtils;

public class PlotIslands extends JavaPlugin implements Listener {
    public static String prefix = "&8[&7PlotIslands&8] ";
    private final String version = getDescription().getVersion();

    public void onEnable() {
        registerCommands();
        Bukkit
            .getConsoleSender()
            .sendMessage(MessageUtils.getColoredMessageWithPrefix("&eEnabled successfully v" + version));
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        Bukkit
            .getConsoleSender()
            .sendMessage(MessageUtils.getColoredMessageWithPrefix("&eDisabled successfully v" + version));
    }

    public void registerCommands() {
        this.getCommand("plotislands").setExecutor(new MainCommand());
    }

    @EventHandler
    public void onWorldCreation(WorldInitEvent event) {
        final String worldName = event.getWorld().getName();
        final String customCommand = "vt toggle " + worldName;

        Bukkit.getScheduler().runTaskLater(this, () -> {
            try {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), customCommand);
                getLogger().info("VT+ toggled for world: " + worldName);
            } catch (Exception e) {
                e.printStackTrace();
                getLogger().severe("Error toggling VT+ for world: " + worldName);
            }
        }, 100L); // 20L = 1 second
    }
}
