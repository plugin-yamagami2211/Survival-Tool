package jp.yama2211.st;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
        } else {
            String FJoin = plugin.getConfig().getString("Msg.FJoin");
            FJoin =FJoin.replace("%player",event.getPlayer().getName());
            FJoin = FJoin.replaceAll("%n","\n");
            event.setJoinMessage(null);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',FJoin));
        }
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event){
        String Quit = plugin.getConfig().getString("Msg.Quit");
        Quit =Quit.replace("%player",event.getPlayer().getName());
        Quit = Quit.replaceAll("%n","\n");
        event.setQuitMessage(null);
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',Quit));
    }

}
