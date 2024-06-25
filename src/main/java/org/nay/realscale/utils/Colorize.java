package org.nay.realscale.utils;


import org.bukkit.ChatColor;

public class Colorize {
    public static String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
