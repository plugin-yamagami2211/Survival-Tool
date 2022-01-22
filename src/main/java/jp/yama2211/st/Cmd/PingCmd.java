package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCmd implements CommandExecutor {
    public Main plugin;
    public PingCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.notgame")));
            return true;
        } else {
            if(args.length == 0){
                Player player = (Player)sender;
                String ping  = Integer.toString(plugin.getPing(player));
                String pingMsg = plugin.getConfig().getString("Ping"+".Msg");
                pingMsg = pingMsg.replace("%Ping" , ping);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',pingMsg));
                return false;
            }

            if(args.length == 1){
                Player player = (Player)sender;
                Player tPlayer = player.getServer().getPlayer(args[0]);
                if(tPlayer == null){
                    sender.sendMessage(tPlayer + "はオンラインではありません。");
                    return true;
                }

                String ping  = Integer.toString(plugin.getPing(tPlayer));
                String pingMsg = plugin.getConfig().getString("Ping"+".Msg2");
                pingMsg = pingMsg.replace("%Ping" , ping);
                pingMsg = pingMsg.replace("%player",tPlayer.getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',pingMsg));
            }

        }
    return false;
    }
}
