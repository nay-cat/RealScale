package org.nay.realscale;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.nay.realscale.command.Scale;
import org.nay.realscale.command.ScaleReload;

import java.io.File;

public final class RealScale extends JavaPlugin {

    public FileConfiguration config;

    @Override
    public void onEnable() {
        setupConfiguration();
        config = getConfig();

        Bukkit.getConsoleSender().sendMessage("ONLY WORKS IN 1.21!! RealScale loaded");
        this.getCommand("scale").setExecutor(new Scale(this));
        this.getCommand("scalereload").setExecutor(new ScaleReload(this));

    }

    public void setupConfiguration() {
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}
