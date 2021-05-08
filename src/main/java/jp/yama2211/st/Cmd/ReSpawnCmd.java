package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReSpawnCmd implements CommandExecutor {
    public Main plugin;
    public ReSpawnCmd(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.notgame")));
            return true;
        } else {
            Player player = (Player)sender;
            try{
                Location ReSpawn = player.getBedSpawnLocation();
                player.teleport(ReSpawn);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("ReSpawn.tp")));
            }catch (Exception err){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("ReSpawn.notp")));
            }
        }

        return false;
    }
}
