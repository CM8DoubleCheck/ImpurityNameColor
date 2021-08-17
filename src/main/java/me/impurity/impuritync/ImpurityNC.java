package me.impurity.impuritync;

import me.impurity.impuritync.command.NameColor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class ImpurityNC extends JavaPlugin {
    private YamlConfiguration players;
    private PluginManager pl;
    private Util util;

    public FileConfiguration getPlayers() {
        return players;
    }

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        players = makeConfig();
        getCommand("nc").setExecutor(new NameColor());
        pl.registerEvents(new NameColor(), this);
        getLogger().info(ChatColor.GREEN + "ImpurityNC by SevJ6 enabled!");
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public void reloadPlayerData() {
        util.loadPlayerData();
        players = util.getPlayerData();
    }
}
