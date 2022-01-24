package jp.yama2211.st;

import jp.yama2211.st.Cmd.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class Main extends JavaPlugin {

    CustomFile config;
    public CustomFile home;
    public CustomFile deathP;
    private String ver;

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Config類
        config = new CustomFile(this);
        saveDefaultConfig();
        home = new CustomFile(this,"home.yml");
        home.saveDefaultConfig();
        deathP = new CustomFile(this,"deathP.yml");
        deathP.saveDefaultConfig();

        ver = Bukkit.getServer().getClass().getPackage().getName();
        ver = ver.substring(ver.lastIndexOf(".") + 1);

        //イベント
        EventListener eventListener = new EventListener(this);
        getServer().getPluginManager().registerEvents(eventListener, this);

        //コマンド
        loadCmd();
        //アップデートの通知
        if (getConfig().getBoolean("Update")) {
            new UpdateChecker(this, "SurvivalTool").getVersion(version -> {
                if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                    getLogger().warning("利用可能なアップデートがあります。配布フォーラムをご確認ください。\nリンク:https://ym21.ml/amc4e");
                }
            });
        }
    }


        @Override
        public void onDisable () {
            // Plugin shutdown logic
        }

        private void loadCmd () {
            getCommand("st").setExecutor(new STCmd(this));
            getCommand("home").setExecutor(new HomeCmd(this));
            getCommand("respawn").setExecutor(new ReSpawnCmd(this));
            getCommand("death").setExecutor(new DeathCmd(this));
            getCommand("skull").setExecutor(new SkullGetCmd(this));
            getCommand("inv").setExecutor(new OpenInvCmd(this));
            getCommand("ping").setExecutor(new PingCmd(this));
            getCommand("ender").setExecutor(new EnderCmd(this));
    }

    public int getPing(Player player) {
        int ping = -1;
        try {
            Class<?> cp = Class.forName("org.bukkit.craftbukkit."+ ver +".entity.CraftPlayer");
            Object cpc = cp.cast(player);
            Method m = cpc.getClass().getMethod("getHandle");
            Object o = m.invoke(cpc);
            Field f = o.getClass().getField("ping");
            ping = f.getInt(o);
        } catch (Exception e) {
            Bukkit.getLogger().warning(e.getLocalizedMessage());
        }
        return ping;
    }

}
