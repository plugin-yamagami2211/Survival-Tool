package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class DeathCmd implements CommandExecutor {
    public Main plugin;

    public DeathCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.notgame")));
                return true;
            } else {
                if(sender.hasPermission("death.use")){
                    Player player = (Player) sender;

                    try {
                        String PlayerId = "DeathPoint." + player.getUniqueId().toString();
                        float yaw = (float) plugin.deathP.getConfig().getDouble(PlayerId + ".Yaw");
                        float pitch = (float) plugin.deathP.getConfig().getDouble(PlayerId + ".Pitch");
                        double x = plugin.deathP.getConfig().getDouble(PlayerId + ".X") + 0.5;
                        double y = plugin.deathP.getConfig().getDouble(PlayerId + ".Y");
                        double z = plugin.deathP.getConfig().getDouble(PlayerId + ".Z") + 0.5;
                        World w = Bukkit.getServer().getWorld(plugin.deathP.getConfig().getString(PlayerId + ".World"));

                        Location point = new Location(w,x,y,z);
                        point.setYaw(yaw);
                        player.getLocation().setPitch(pitch);

                        player.teleport(point);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Death.tp")));
                    } catch (Exception err) //homeが存在しないとTPせずにメッセージを出す
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Death.notp")));
                    }
                }

            }

        return false;
    }
}