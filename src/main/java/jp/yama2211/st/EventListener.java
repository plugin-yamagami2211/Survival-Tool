package jp.yama2211.st;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
    public Main plugin;
    public EventListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event){
        if(event.getPlayer().hasPlayedBefore()){
            String Join = plugin.getConfig().getString("Msg.Join");
            Join =Join.replace("%player",event.getPlayer().getName());
            Join = Join.replaceAll("%n","\n");
            event.setJoinMessage(null);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',Join));
        }
    }

}
