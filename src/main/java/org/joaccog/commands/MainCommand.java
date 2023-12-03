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
            sender.sendMessage(MessageUtils.getColoredMessageWithPrefix("&cThis action can only be performed by a player"));
            return true;
        }

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("help")) {
                // /pi help
            } else if (args[0].equalsIgnoreCase("list")) {
                // /pi list
            } else if (args[0].equalsIgnoreCase("create")) {
                // /pi create
            }
        }
        return true;
    }
}

