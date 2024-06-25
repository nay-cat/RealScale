package org.nay.realscale.command;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.nay.realscale.RealScale;
import org.nay.realscale.utils.Colorize;

import java.util.Objects;

public class Scale implements CommandExecutor {

    private RealScale plugin;

    public Scale(RealScale plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command");
            return true;
        }
        FileConfiguration config = plugin.getConfig();
        Player player = (Player) commandSender;
        if (!player.hasPermission("scale.scale")){
            player.sendMessage(Colorize.Color(config.getString("Messages.no-permission")));
            return true;
        }
        if (args.length > 0) {
            try {
                double maximumMeasure = config.getDouble("Config.maximum-measure"),
                        minimumMeasure = config.getDouble("Config.minimum-measure"),
                        measure = Double.parseDouble(args[0]),
                        result = (measure * 0.51) / 100;
                if (measure < maximumMeasure && measure > minimumMeasure) {
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(result);
                    player.sendMessage(Colorize.Color(config.getString("Messages.scale-change")).replaceAll("%scale%", String.valueOf(result)).replaceAll("%player%", player.getDisplayName()).replaceAll("%measure%", args[0]));
                } else {
                    player.sendMessage(Colorize.Color(config.getString("Messages.exceeded-measure")));
                }
            } catch (NumberFormatException e) {
                player.sendMessage(Colorize.Color(config.getString("Messages.invalid-measure")));
            }
        } else {
            player.sendMessage(Colorize.Color(config.getString("Messages.no-params")));
        }
        return false;
    }
}
