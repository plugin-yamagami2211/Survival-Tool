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
import org.bukkit.event.block.BlockCanBuildEvent;

public class WorldTeleportCmd implements CommandExecutor {
    public Main plugin;

    public WorldTeleportCmd(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin.notgame")));
        } else {
            if(args.length == 0){
                sender.sendMessage("/wtp tp <worldname> : <worldname>にテレポートします。");
                sender.sendMessage("/wtp set");
                return false;
            }

            if (args[0].equalsIgnoreCase("set")) {
                Player player = (Player) sender;
                Location loc = player.getLocation();

                String world = "wtp." + loc.getWorld().getName().toString();
                plugin.worldP.getConfig().set(world + ".X", loc.getBlockX());
                plugin.worldP.getConfig().set(world + ".Y", loc.getBlockY());
                plugin.worldP.getConfig().set(world + ".Z", loc.getBlockZ());
                plugin.worldP.getConfig().set(world + ".Yaw", loc.getYaw());
                plugin.worldP.getConfig().set(world + ".Pitch", loc.getPitch());
                plugin.worldP.getConfig().set(world + ".World", loc.getWorld().getName());
                plugin.worldP.saveConfig();

                player.sendMessage("[WorldTP] ポイントを設定しました。");
                return true;
            }
            if (args[0].equalsIgnoreCase("tp")) {
                Player player = (Player) sender;
                try {
                    World w = Bukkit.getWorld(args[1]);
                    try {
                        String world = "wtp." + w.getName().toString();
                        float yaw = (float) plugin.worldP.getConfig().getDouble(world + ".Yaw");
                        float pitch = (float) plugin.worldP.getConfig().getDouble(world + ".Pitch");
                        double x = plugin.worldP.getConfig().getDouble(world + ".X") + 0.5;
                        double y = plugin.worldP.getConfig().getDouble(world + ".Y");
                        double z = plugin.worldP.getConfig().getDouble(world + ".Z")+ 0.5;

                        Location loc = new Location(w, x, y, z);
                        loc.setYaw(yaw);
                        loc.setPitch(pitch);
                        player.teleport(loc);

                        sender.sendMessage("[WorldTP] ");
                    } catch (Exception e) {
                        Location loc = new Location(w.getSpawnLocation().getWorld(),w.getSpawnLocation().getBlockX(),w.getSpawnLocation().getBlockY(),w.getSpawnLocation().getBlockZ());
                        loc.setYaw(w.getSpawnLocation().getYaw());
                        loc.setPitch(w.getSpawnLocation().getPitch());
                        player.teleport(loc);
                    }
                } catch (Exception e) {
                    sender.sendMessage("not world (error1)");
                }

            }

            return false;
        }
        return false;
    }
}

