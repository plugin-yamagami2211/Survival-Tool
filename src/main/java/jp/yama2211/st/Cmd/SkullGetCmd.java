package jp.yama2211.st.Cmd;

import jp.yama2211.st.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullGetCmd implements CommandExecutor {
    public Main plugin;

    public SkullGetCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED+"ゲーム内から実行してください。");
            return true;
        } else {
            Player player = (Player)sender;
            if(player.hasPermission("skull.get")){
                if(args.length == 0){
                    sender.sendMessage(ChatColor.GREEN + "/skull <Player> でプレイヤーの頭を入手できます。");
                } else {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD,1);
                    SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
                    skullMeta.setDisplayName(ChatColor.YELLOW + args[0] + "の頭");
                    skullMeta.setOwner(args[0]);
                    skull.setItemMeta(skullMeta);
                    player.getInventory().addItem(new ItemStack[] {skull});
                    player.updateInventory();
                    player.sendMessage(ChatColor.GREEN + args[0] + "の頭を与えました。");

                }
            }
        }
        return false;
    }
}
