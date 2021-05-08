package jp.yama2211.st;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class CustomFile {

    /*
    @author HimaJyun
    https://jyn.jp/bukkit-plugin-development-8/
    */
    private FileConfiguration config = null;
    private final File configFile;
    private final String file;
    private final Plugin plugin;

    /**
     * config.ymlを設定として読み書きするカスタムコンフィグクラスをインスタンス化します。
     *
     * @param plugin
     *            ロード対象のプラグイン
     */
    public CustomFile(Plugin plugin) {
        this(plugin, "config.yml");
    }

    /**
     * 指定したファイル名で設定を読み書きするカスタムコンフィグクラスをインスタンス化します。
     *
     * @param plugin
     *            ロード対象のプラグイン
     * @param fileName
     *            読み込みファイル名
     */
    public CustomFile(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.file = fileName;
        configFile = new File(plugin.getDataFolder(), file);
    }

    /**
     * デフォルト設定を保存します。
     */
    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            plugin.saveResource(file, false);
        }
    }

    /**
     * 読み込んだFileConfiguretionを提供します。
     *
     * @return 読み込んだ設定
     */
    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    /**
     * 設定を保存します。
     */
    public void saveConfig() {
        if (config == null) {
            return;
        }
        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
        }
    }

    /**
     * 設定をリロードします。
     */
    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);

        final InputStream defConfigStream = plugin.getResource(file);
        if (defConfigStream == null) {
            return;
        }

        config.setDefaults(
                YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)));
    }
}
