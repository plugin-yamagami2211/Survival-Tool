package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderCmd implements CommandExecutor {
    public Main plugin;

    public EnderCmd(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.notgame")));
            return true;
        } else {
            if (sender.hasPermission("st.openender")) {
                if(args.length == 0){
                    sender.sendMessage("/ender <PlayerName>");
                    return false;
                }

                if(args.length == 1){
                    try{
                        Player player = (Player)sender;
                        Player tPlayer = player.getServer().getPlayer(args[0]);
                        player.openInventory(tPlayer.getEnderChest());
                        String Msg = plugin.getConfig().getString("Msg"+".Ender");
                        Msg = Msg.replace("%player",tPlayer.getName());
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',Msg));
                        return false;
                    }catch (Exception e){
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
