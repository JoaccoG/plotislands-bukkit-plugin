package org.joaccog.commands;

import org.jetbrains.annotations.NotNull;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.joaccog.utils.MessageUtils;

public class MainCommand implements CommandExecutor {
    private final String featureOnDevelopment = MessageUtils.getColoredMessageWithPrefix("&6Feature in development...");
    private final String unknownCommand = MessageUtils.getColoredMessageWithPrefix("&cUnknown command. use &6/pi help&c for more information.");

    @Override
    public boolean onCommand(
        @NotNull CommandSender sender,
        @NotNull Command command,
        @NotNull String alias,
        String[] args
    ) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            help(sender);
            return true;
        } else if (args.length == 1) {
            // TODO.
            //  Call desired methods when developed.
            //  Each method should return boolean true or boolean false to
            //  send one message on success and other on failure.
            return switch (args[0]) {
                case "worlds", "commands", "run" -> {
                    sender.sendMessage(featureOnDevelopment);
                    yield true;
                }
                default -> {
                    sender.sendMessage(unknownCommand);
                    yield false;
                }
            };
        } else {
            sender.sendMessage(unknownCommand);
            return false;
        }
    }

    public void help(CommandSender sender) {
        sender.sendMessage(MessageUtils.getColoredMessage("&8&l==========&e&l List of Commands&8&l =========="));
        sender.sendMessage(MessageUtils.getColoredMessage("&b /pi help"));
        sender.sendMessage(MessageUtils.getColoredMessage("&8&l|&7 - Get this help page."));
        sender.sendMessage(MessageUtils.getColoredMessage("&b /pi commands"));
        sender.sendMessage(MessageUtils.getColoredMessage("&8&l|&7 - Get the list of commands that runs on new worlds creation."));
        // TODO: Remove comment when methods are developed.
        // sender.sendMessage(MessageUtils.getColoredMessage("&7 /pi cmd add &8[&bcommand&8]"));
        // sender.sendMessage(MessageUtils.getColoredMessage("&8&l|&7 - Add a new command to be executed on new worlds creation."));
        // sender.sendMessage(MessageUtils.getColoredMessage("&7 /pi cmd remove &8[&bcommand&8]"));
        // sender.sendMessage(MessageUtils.getColoredMessage("&8&l|&7 - Remove a command that runs on new worlds creation."));
        sender.sendMessage(MessageUtils.getColoredMessage("&b /pi worlds"));
        sender.sendMessage(MessageUtils.getColoredMessage("&8&l|&7 - Get the list of worlds where the commands have already been executed in."));
        // TODO: Remove comment when methods are developed.
        // sender.sendMessage(MessageUtils.getColoredMessage("&b /pi world add &8[&bworld&8]"));
        // sender.sendMessage(MessageUtils.getColoredMessage("&8&l|&7 - Prevent a&c not generated&8 world to run the list of commands."));
        // sender.sendMessage(MessageUtils.getColoredMessage("&b /pi world remove &8[&bworld&8]"));
        // sender.sendMessage(MessageUtils.getColoredMessage("&8&l|&7 - Remove a world (that already run the commands) of the worlds list."));
        sender.sendMessage(MessageUtils.getColoredMessage("&b /pi run &8[&bworld&8]"));
        sender.sendMessage(MessageUtils.getColoredMessage("&8&l|&7 - Run the list of commands &nagain&7 for the specified world."));
        sender.sendMessage(MessageUtils.getColoredMessage("&8&l==========&e&l List of Commands&8&l =========="));
    }
}
