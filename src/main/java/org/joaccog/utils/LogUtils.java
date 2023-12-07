package org.joaccog.utils;

import org.joaccog.PlotIslands;

import static org.bukkit.Bukkit.getLogger;

public class LogUtils {

    public static void info(String message) {
        getLogger()
            .info(
                PlotIslands.prefix + message
            );
    }

    public static void warning(String message) {
        getLogger()
            .warning(
                PlotIslands.prefix + message
            );
    }

    public static void severe(String message) {
        getLogger()
            .severe(
                PlotIslands.prefix + message
            );
    }
}
