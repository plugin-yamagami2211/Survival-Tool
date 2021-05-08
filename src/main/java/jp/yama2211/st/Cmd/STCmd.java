package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class STCmd implements CommandExecutor {
    public Main plugin;
    public STCmd(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("st")){
            if (args.length == 0) {
                PluginDescriptionFile yml = plugin.getDescription();
                sender.sendMessage(ChatColor.AQUA + "/*** " + yml.getName() + "***/");
                sender.sendMessage(ChatColor.GREEN + "コマンド:");
                sender.sendMessage(ChatColor.WHITE + "/st reload :" + ChatColor.GOLD + "configをリロードします。");
                sender.sendMessage(ChatColor.WHITE + "/home :" + ChatColor.GREEN + "設定した場所にTPします。");
                sender.sendMessage(ChatColor.WHITE + "/home set :" + ChatColor.GREEN + "現在座標をHomeに記録します。");

                return true;
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if ((sender.hasPermission("st.admin")) || (sender.isOp())) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.reload")));
                        plugin.reloadConfig();
                        plugin.home.reloadConfig();
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.notpex")));
                    }
                }
            }

        }
        return false;
    }
}
