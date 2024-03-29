package jp.yama2211.st;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;

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
        plugin.deathP.saveConfig();
    }

    //権限のないプレイヤーに
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(plugin.getConfig().getBoolean("Ignore")){
            Player player = event.getPlayer();

            if(!player.hasPermission("st.ignore.chat")){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onNotBreak(BlockBreakEvent event){
        if(plugin.getConfig().getBoolean("Ignore")) {
            Player player = event.getPlayer();

            if (!player.hasPermission("st.ignore.blockbreak")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onNotPut(BlockPlaceEvent event){
        if(plugin.getConfig().getBoolean("Ignore")) {
            Player player = event.getPlayer();

            if (!player.hasPermission("st.ignore.blockplace")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onNotItemDrop(PlayerDropItemEvent event){
        if(plugin.getConfig().getBoolean("Ignore")){
            Player player = event.getPlayer();

            if(!player.hasPermission("st.ignore.itemdrop")){
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onNotItemPicup(PlayerPickupItemEvent event){
        if(plugin.getConfig().getBoolean("Ignore")){
            Player player = event.getPlayer();

            if(!player.hasPermission("st.ignore.itempickup")){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onNotArrowPickup(PlayerPickupArrowEvent event){
        if(plugin.getConfig().getBoolean("Ignore")){
            Player player = event.getPlayer();

            if(!player.hasPermission("st.arrowpickup.ignore")){
                event.setCancelled(true);
            }
        }
    }

    //チェスト
    @EventHandler
    public void onNotInvChest(InventoryOpenEvent event){
        if(plugin.getConfig().getBoolean("Ignore")){
            if(event.getInventory().getType().equals(InventoryType.CHEST)){
                Player player = (Player)event.getPlayer();

                if(!player.hasPermission("st.ignore.inventory.chest")){
                    event.setCancelled(true);
                }
            }
        }
    }

    //エンダーチェスト
    @EventHandler
    public void onNotInvEnderChest(InventoryOpenEvent event){
        if(plugin.getConfig().getBoolean("Ignore")){
            if(event.getInventory().getType().equals(InventoryType.ENDER_CHEST)){
                Player player = (Player)event.getPlayer();

                if(!player.hasPermission("st.ignore.inventory.enderchest")){
                    event.setCancelled(true);
                }
            }
        }
    }



}
