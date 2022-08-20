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
                String wSLx = Integer.toString(w.getSpawnLocation().getBlockX());
                String wSLy = Integer.toString(w.getSpawnLocation().getBlockY());
                String wSLz = Integer.toString(w.getSpawnLocation().getBlockZ());
                Boolean wAllowAnimal = w.getAllowAnimals();
                Boolean wAllowMonsters = w.getAllowMonsters();
                Boolean wPvP = w.getPVP();
                String wDifficulty = w.getDifficulty().toString();
                String wAmbient = Integer.toString(w.getAmbientSpawnLimit());
                String wAnimal = Integer.toString(w.getAnimalSpawnLimit());
                String wMonster = Integer.toString(w.getMonsterSpawnLimit());
                String wMaxHeight = Integer.toString(w.getMaxHeight());
                String wMinHeight = Integer.toString(w.getMinHeight());

                String wMsg = plugin.getConfig().getString("WInfo.msg");
                wMsg = wMsg.replace("%Name",wName); //WorldName
                wMsg = wMsg.replace("%SLx",wSLx); //SpawnLocation x
                wMsg = wMsg.replace("%SLy",wSLy); //SpawnLocation y
                wMsg = wMsg.replace("%SLz",wSLz); //SpawnLocation z
                wMsg = wMsg.replace("%AllowAnimal",wAllowAnimal.toString()); //Animal Spawn Boolean
                wMsg = wMsg.replace("%AllowMonsters",wAllowMonsters.toString()); //Monster Spawn Boolean
                wMsg = wMsg.replace("%PvP",wPvP.toString()); //PvPBoolean
                wMsg = wMsg.replace("%Difficulty",wDifficulty); //Diffculty
                wMsg = wMsg.replace("%Ambient",wAmbient); //Ambient Spawn Limit
                wMsg = wMsg.replace("%Animal",wAnimal); //Animal Spawn Limit
                wMsg = wMsg.replace("%Monster",wMonster); //Monster Spawn Limit
                wMsg = wMsg.replace("%MaxHeight",wMaxHeight); //World Y Max
                wMsg = wMsg.replace("%MinHeight",wMinHeight); //World Y Min

                wMsg = wMsg.replaceAll("%n","\n");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',wMsg));
            }catch (Exception e){
                sender.sendMessage("[Error] " + args[0] + " は存在しません。");
            }
        }
        return false;
    }
}
