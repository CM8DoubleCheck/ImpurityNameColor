package me.impurity.impuritync.command;

import me.impurity.impuritync.ImpurityNC;
import me.impurity.impuritync.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NameColor implements CommandExecutor {

    private final ImpurityNC inc;
    private final Util util;
    public NameColor(ImpurityNC inc) {
        this.inc = inc;
        util = inc.getUtil();
    }

    private final String AVAILABLE_COLORS = "&4dark_red &cred &6gold &eyellow &2dark_green &agreen &baqua &3dark_aqua &1dark_blue &9blue &dlight_purple &5dark_purple &7gray &8dark_gray &0black&r &4r&6a&ei&2n&9b&1o&5w&r &lbold&r &mstrikethrough&r &nunderline&r &oitalic&r random reset";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            util.sendMessage(sender, "&cYou must be a player to do that!");
        } else {
            Player player = (Player) sender;
            if (player.hasPermission("ImpurityNC.use")) {
                if (args.length > 0) {

                    StringBuilder builder = new StringBuilder();
                    List<Character> chars = new ArrayList<>();
                    List<ChatColor> colors;

                    switch(args[0].toLowerCase()) {
                        case "random":
                            /*
                            Arguments "random" and "rainbow" are inspired from an open source
                            name color plugin, "LeeesNC."
                            You can view LeeesNC at https://github.com/XeraPlugins/LeeesNC
                            */
                             colors = Arrays.asList(ChatColor.values());

                            for (int i = 0; i < player.getName().length(); i++) {
                                char c = player.getName().charAt(i);
                                chars.add(c);
                            }

                            for (int i = 0; i < chars.size(); i++) {
                                if (i == colors.size()) {
                                    i = 0;
                                }
                                int index = ThreadLocalRandom.current().nextInt(colors.size());
                                builder.append(colors.get(index)).append(chars.get(i));
                            }
                            setDisplayName(player, builder.toString());
                            break;
                        case "rainbow":
                            colors = Arrays.asList(ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.DARK_GREEN, ChatColor.BLUE, ChatColor.DARK_BLUE, ChatColor.DARK_PURPLE);

                            for (int i = 0; i < player.getName().length(); i++) {
                                char c = player.getName().charAt(i);
                                chars.add(c);
                            }

                            for (int i = 0; i < chars.size(); i++) {
                                if (i == colors.size()) {
                                    i = 0;
                                }
                                builder.append(colors.get(i)).append(chars.get(i));
                            }
                            setDisplayName(player, builder.toString());
                            break;
                        default:
                            try {
                                for (String arg : args) {
                                    ChatColor c = ChatColor.valueOf(arg.toUpperCase().replace("magic", ""));
                                    builder.append(c);
                                }
                                setDisplayName(player, builder.toString());
                            } catch (IllegalArgumentException ignored) {
                                util.sendMessage(player, "&4Invalid color type!");
                                util.sendMessage(player, "&6Available formats:" + "\n" + AVAILABLE_COLORS);
                            }
                            break;
                    }
                } else {
                    util.sendMessage(player, "&6Available colors:" + "\n" + AVAILABLE_COLORS);
                }
            } else {
                util.sendMessage(player, "&6Sorry, you don't have access to change your name color!");
            }
        }
        return true;
    }

    private void setDisplayName(Player player, String name) {
        name = name.replace("§k", "") + ChatColor.RESET;
        player.setDisplayName(name);
        util.sendMessage(player, "&6Your name is now: &r" + name);
        inc.getPlayers().set(String.valueOf(player.getUniqueId()), name);
        inc.saveConfig();
        inc.reloadPlayerData();
    }
}
