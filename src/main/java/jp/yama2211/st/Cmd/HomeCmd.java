package jp.yama2211.st.Cmd;

import jp.yama2211.st.CustomFile;
import jp.yama2211.st.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCmd implements CommandExecutor {
    public Main plugin;

    public HomeCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.notgame")));
            return true;
        } else {
            if(cmd.getName().equalsIgnoreCase("home")){
            if(sender.hasPermission("home.use"))
            {
                if (args.length == 0) {
                try{
                    Player player = (Player)sender;
                    String playerId = "Home." + player.getUniqueId().toString();
                    float yaw = (float) plugin.home.getConfig().getDouble(playerId + ".Yaw");
                    float pitch = (float) plugin.home.getConfig().getDouble(playerId + ".Pitch");
                    double x = plugin.home.getConfig().getDouble(playerId + ".X") + 0.5;
                    double y = plugin.home.getConfig().getDouble(playerId + ".Y");
                    double z = plugin.home.getConfig().getDouble(playerId + ".Z") + 0.5;
                    World w = Bukkit.getServer().getWorld( plugin.home.getConfig().getString(playerId + ".World") );

                    Location point = new Location(w,x,y,z);
                    point.setYaw(yaw);
                    point.setPitch(pitch);
                    player.teleport(point);

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("home.tp")));

                }catch (Exception err){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("home.nottp")));
                }
                } //args ==0
            } //hasPermission
                else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.notpex")));
            }
            } //cmd.getName

            if (args.length == 1) {
                if(sender.hasPermission("home.use")){
                    Player player = (Player)sender;
                    String playerId = "Home." + player.getUniqueId().toString();
                    plugin.home.getConfig().set(playerId + ".X", player.getLocation().getBlockX());
                    plugin.home.getConfig().set(playerId + ".Y", player.getLocation().getBlockY());
                    plugin.home.getConfig().set(playerId + ".Z", player.getLocation().getBlockZ());
                    plugin.home.getConfig().set(playerId + ".Yaw", player.getLocation().getYaw());
                    plugin.home.getConfig().set(playerId + ".Pitch", player.getLocation().getPitch());
                    plugin.home.getConfig().set(playerId + ".World", player.getLocation().getWorld().getName());
                    plugin.home.saveConfig();

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("home.set")));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.notpex")));
                }
            }

        } //else
        return false;
    }
}
