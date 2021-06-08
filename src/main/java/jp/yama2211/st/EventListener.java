package jp.yama2211.st;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
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

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        String PlayerId = "DeathPoint." + player.getUniqueId().toString();
        plugin.deathP.getConfig().set(PlayerId + ".X", player.getLocation().getBlockX());
        plugin.deathP.getConfig().set(PlayerId + ".Y", player.getLocation().getBlockY());
        plugin.deathP.getConfig().set(PlayerId + ".Z", player.getLocation().getBlockZ());
        plugin.deathP.getConfig().set(PlayerId + ".Yaw", player.getLocation().getYaw());
        plugin.deathP.getConfig().set(PlayerId + ".Pitch", player.getLocation().getPitch());
        plugin.deathP.getConfig().set(PlayerId + ".World", player.getLocation().getWorld().getName());
    }

    //権限のないプレイヤーに
    /*
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();

        if(!player.hasPermission("st.chat.ignore")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onNotBreak(BlockBreakEvent event){
        Player player = event.getPlayer();

        if(!player.hasPermission("st.blockbreak.ignore")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onNotPut(BlockPlaceEvent event){
        Player player = event.getPlayer();

        if(!player.hasPermission("st.blockplace.ignere")){
            event.setCancelled(true);
        }
    }
*/
}
