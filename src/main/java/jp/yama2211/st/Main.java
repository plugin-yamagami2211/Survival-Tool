package jp.yama2211.st;

import jp.yama2211.st.Cmd.HomeCmd;
import jp.yama2211.st.Cmd.STCmd;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    CustomFile config;
    public CustomFile home;

    @Override
    public void onEnable() {
        // Plugin startup logic
        //Config類
        config = new CustomFile(this);
        saveDefaultConfig();
        home = new CustomFile(this,"home.yml");
        home.saveDefaultConfig();

        //イベント
        EventListener eventListener = new EventListener(this);
        getServer().getPluginManager().registerEvents(eventListener, this);

        //コマンド
        loadCmd();
        //アップデートの通知
        if (getConfig().getBoolean("Update")) {
            new UpdateChecker(this, "HandItemHide").getVersion(version -> {
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
        }
}
