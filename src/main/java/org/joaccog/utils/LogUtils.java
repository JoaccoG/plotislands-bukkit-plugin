package org.joaccog.utils;

import static org.bukkit.Bukkit.getLogger;

public class LogUtils {

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void warning(String message) {
        getLogger().warning(message);
    }

    public static void severe(String message) {
        getLogger().severe(message);
    }
}
