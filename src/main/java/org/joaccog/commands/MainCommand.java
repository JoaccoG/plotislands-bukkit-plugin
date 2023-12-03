package org.joaccog.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.joaccog.utils.MessageUtils;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cThis action can only be performed by a player"));
            return true;
        }

        sender.sendMessage(MessageUtils.getColoredMessage("&7Information about PlotIslands..."));
        return true;
    }
}
