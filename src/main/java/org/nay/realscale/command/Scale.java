package org.nay.realscale.command;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.nay.realscale.RealScale;
import org.nay.realscale.utils.Colorize;

public class Scale implements CommandExecutor {

    private final RealScale plugin;

    public Scale(RealScale plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only players can use this command");
            return true;
        }

        FileConfiguration config = plugin.getConfig();
        if (!player.hasPermission("scale.scale")) {
            player.sendMessage(Colorize.Color(config.getString("Messages.no-permission")));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(Colorize.Color(config.getString("Messages.no-params")));
            return false;
        }

        try {
            double maximumMeasure = config.getDouble("Config.maximum-measure"),
                    minimumMeasure = config.getDouble("Config.minimum-measure"),
                    measure = Double.parseDouble(args[0]),
                    result = (measure * 0.51) / 100;
            if (measure < maximumMeasure && measure > minimumMeasure) {

                AttributeInstance scale = player.getAttribute(Attribute.GENERIC_SCALE);

                // It may be unlikely, but it's better to be sure.
                if (scale == null) {
                    player.sendMessage(Colorize.Color(config.getString("Messages.no-scale-attribute")));
                    return true;
                }

                scale.setBaseValue(result);
                player.sendMessage(Colorize.Color(
                        config.getString("Messages.scale-change"),
                        "scale", String.valueOf(result),
                        "player", player.getDisplayName(),
                        "measure", args[0]
                ));
            } else {
                player.sendMessage(Colorize.Color(config.getString("Messages.exceeded-measure")));
            }
        } catch (NumberFormatException e) {
            player.sendMessage(Colorize.Color(config.getString("Messages.invalid-measure")));
        }
        return true;
    }
}
