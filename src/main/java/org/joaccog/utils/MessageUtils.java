package org.joaccog.utils;

import org.bukkit.ChatColor;
import org.joaccog.PlotIslands;

public class MessageUtils {
    public static String getColoredMessage(String message) {
        return ChatColor.translateAlternateColorCodes(
            '&', PlotIslands.prefix + message);
    }
}
