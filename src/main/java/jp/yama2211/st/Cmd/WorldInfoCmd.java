package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorldInfoCmd implements CommandExecutor {
    public Main plugin;

    public WorldInfoCmd(Main plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage("/winfo <worldname>");
        }
        if(args.length == 1){
            try{
                World w = Bukkit.getWorld(args[0]);
                String wName = w.getName(); //WorldName
                String wSL = w.getSpawnLocation().toString(); //SpwanLocation(toString)
                Boolean wAllowAnimal = w.getAllowAnimals();
                Boolean wAllowMonsters = w.getAllowMonsters();
                Boolean wPvP = w.getPVP();
                String wDifficulty = w.getDifficulty().toString();

                String wMsg = plugin.getConfig().getString("WInfo.msg");
                wMsg = wMsg.replace("%Name",wName);
                wMsg = wMsg.replace("%SL",wSL);
                wMsg = wMsg.replace("%AllowAnimal",wAllowAnimal.toString());
                wMsg = wMsg.replace("%AllowMonsters",wAllowMonsters.toString());
                wMsg = wMsg.replace("%PvP",wPvP.toString());
                wMsg = wMsg.replace("%Difficulty",wDifficulty);
                wMsg = wMsg.replaceAll("%n","\n");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',wMsg));
            }catch (Exception e){
                sender.sendMessage("[Error] " + args[0] + " は存在しません。");
            }
        }
        return false;
    }
}
