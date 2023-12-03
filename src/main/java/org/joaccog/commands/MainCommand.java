package org.joaccog.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.joaccog.PlotIslands;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            // Console
            sender.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&', String.format("%s&eThis action can only be performed by a player", PlotIslands.prefix)
                )
            );
            return true;
        }
        // Player

        return true;
    }
}
