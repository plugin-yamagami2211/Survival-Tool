package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmChangeCmd implements CommandExecutor {
    public Main plugin;

    public GmChangeCmd(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.notgame")));
            return true;
        } else {
            Player player = (Player)sender;
            if(args.length == 0){
                player.sendMessage("/gm <0/s | 1/c | 2/a / 3/ss>");
                return false;
            }

            if(args.length == 1){
                if(player.hasPermission("st.gamemode.survival")){
                    if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")){
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Gamemode"+".Survival")));
                        return false;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.notpex")));
                }

                if(player.hasPermission("st.gamemode.creative")){
                    if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")){
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Gamemode"+".Creative")));
                        return false;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.notpex")));
                }

                if(player.hasPermission("st.gamemode.adventure")){
                    if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")){
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Gamemode"+".Adventure")));
                        return false;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.notpex")));
                }

                if(player.hasPermission("st.gamemode.spectator")){
                    if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("ss")){
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Gamemode"+".Spectator")));
                        return false;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("plugin.notpex")));
                }
            }
        }

        return false;
    }
}
