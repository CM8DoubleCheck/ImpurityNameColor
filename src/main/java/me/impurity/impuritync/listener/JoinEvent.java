package me.impurity.impuritync.listener;

import me.impurity.impuritync.ImpurityNC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    private final ImpurityNC inc;
    public JoinEvent(ImpurityNC inc) {
        this.inc = inc;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (inc.getPlayers().contains(String.valueOf(player.getUniqueId()))) {
            if (player.hasPermission("ImpurityNC.use")) {
                player.setDisplayName(inc.getPlayers().getString(String.valueOf(player.getUniqueId())) + ChatColor.RESET);
            } else {
                player.setDisplayName(player.getName());
            }
        }
    }

}
