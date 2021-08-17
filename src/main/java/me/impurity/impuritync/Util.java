package me.impurity.impuritync;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Util {

    private final File playersFile;
    private YamlConfiguration playerData;

    public Util(ImpurityNC inc) {
        playersFile = new File(inc.getDataFolder(), "players.yml");
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void sendMessage(Player player, String message) {
        sendMessage((CommandSender) player, message);
    }

    public void loadPlayerData() {
        try {
            if (!playersFile.exists()) playersFile.createNewFile();
            playerData = new YamlConfiguration();
            playerData.load(playersFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void savePlayerData() {
        try {
            playerData.save(playersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getPlayerData() {
        return playerData;
    }

}
