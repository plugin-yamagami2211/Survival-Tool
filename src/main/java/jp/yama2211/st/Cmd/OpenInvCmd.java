package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenInvCmd  implements CommandExecutor {
    public Main plugin;

    public OpenInvCmd(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.notgame")));
            return true;
        } else {
            if (sender.hasPermission("st.openinv")) {
                if(args.length == 0){
                    sender.sendMessage("/inv <PlayerName>");
                    return false;
                }
                if (args.length == 1) {
                    try {
                        Player player = (Player) sender;
                        Player tPlayer = player.getServer().getPlayer(args[0]);
                        player.openInventory(tPlayer.getInventory());
                        String InvMsg = this.plugin.getConfig().getString("Msg" + ".Inv");
                        InvMsg = InvMsg.replace("%player", tPlayer.getName());
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', InvMsg));
                        return false;
                    } catch (Exception err){
                        String ErrMsg = plugin.getConfig().getString("Msg"+".OfErr");
                        ErrMsg = ErrMsg.replace("%player",args[0]);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',ErrMsg));
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.notpex")));
            }
        }
        return false;
    }
}
