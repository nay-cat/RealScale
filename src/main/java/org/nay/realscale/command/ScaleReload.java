package org.nay.realscale.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nay.realscale.RealScale;

public class ScaleReload implements CommandExecutor {

    private final RealScale plugin;

    public ScaleReload(RealScale plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command");
            return true;
        }

        Player player = (Player) commandSender;

        if (player.hasPermission("scale.reload")) {
            plugin.reloadConfig();
            player.sendMessage("RealScale reloaded");
            return true;
        }

        return false;
    }
}
