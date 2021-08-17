package me.impurity.impuritync;

import me.impurity.impuritync.command.NameColor;
import me.impurity.impuritync.listener.JoinEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ImpurityNC extends JavaPlugin {
    private YamlConfiguration players;
    private PluginManager pl;
    private Util util;

    public FileConfiguration getPlayers() {
        return players;
    }

    @Override
    public void onEnable() {

        if (!getDataFolder().exists()) getDataFolder().mkdir();

        util = new Util(this);
        pl = getServer().getPluginManager();
        reloadPlayerData();

        getCommand("nc").setExecutor(new NameColor(this));
        pl.registerEvents(new JoinEvent(this), this);
        getLogger().info(ChatColor.GREEN + "ImpurityNC by SevJ6 enabled!");
    }

    @Override
    public void onDisable() {
        util.savePlayerData();
    }

    public void reloadPlayerData() {
        util.loadPlayerData();
        players = util.getPlayerData();
    }

    public Util getUtil() {
        return util;
    }
}
