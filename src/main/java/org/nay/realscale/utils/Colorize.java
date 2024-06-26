package org.nay.realscale.utils;

import org.bukkit.ChatColor;

public final class Colorize {

    private Colorize() {
        throw new IllegalStateException("Utility class");
    }

    public static String Color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String Color(String input, String... replacements) {
        if (replacements.length % 2 != 0) {
            throw new IllegalArgumentException("Replacements must be even");
        }
        String result = input;
        for (int i = 0; i < replacements.length; i += 2) {
            result = result.replace("%" + replacements[i] +  "%", replacements[i + 1]);
        }
        return ChatColor.translateAlternateColorCodes('&', result);
    }
}
