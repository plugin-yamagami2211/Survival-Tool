# Survival-Tool
サバイバルを遊びやすくするかもしれないプラグインです。  

# 機能
## home機能
設定した任意の場所のTPすることができます。
どこのディメンションでも設定可能です。

## リスポーンにTPする機能
ベットなどのリスポーンポイントにTPすることができます。
  
## 直前の死亡したポイントにTPする機能
直前の死亡したポイントにTPします。

## コマンド
`/st` : プラグインのヘルプコマンド  
`/st reload` : Configをリロードする  
`/home` : 設定した場所にTPする。  
`/home set`: 現在座標を記録する(home用)  
`/respawn` : リスポーンポイントにTPする。  
`/death` : 直前に死亡したポイントにTPする  
`/inv <プレイヤー名>` : プレイヤーのインベントリーを開く。  
`/ping (プレイヤー名)` : pingを確認できます。またプレイヤー名を入力するとその人のpingを表示できます。  
`/ender <プレイヤー名>` : プレイヤーのインベントリーを開く。  
`/gm <0/s | 1/c | 2/a / 3/ss>` : ゲームモードを変更。  
`/wtp tp <worldname>` : <worldname>にTPします。設定されたポイントがあればそこに。なければスポーンポイントにTPします。  
`/wtp set` : 現在座標を記録する(wtp用)。
## コマンドPex
太字は誰でも使えるコマンドです。

| cmd       | Pex                                                                                                                       | 
|:----------|:--------------------------------------------------------------------------------------------------------------------------|
| home      | **home.use**                                                                                                              | 
| respawn   | **respawn.use**                                                                                                           |
| death     | **death.use**                                                                                                             |
| inv       | st.openinv                                                                                                                |
| ping      | **st.ping**<br/>st.tping                                                                                                  |
| ender     | st.openender                                                                                                              |
| gm        | st.gamemode.* (全権限)<br/>st.gamemode.survival<br/>st.gamemode.creative<br/>st.gamemode.adventure<br/>st.gamemode.spectator |
| skull     | **skull.get**                                                                                                             |
| wtp       | (現在Pexなし)                                                                                                                 |
| winfo     | (現在Pexなし)                                                                                                                 |
| st reload | st.admin                                                                                                                  |

## 権限がないとできない系
Permissionがないと次のことができなくなります。  
(※最初からは使えないので**Configで有効**にする必要があります)

| #            | Pex                            | 
|:-------------|:-------------------------------|
| チャット         | st.ignore.chat                 |
| 破壊           | st.ignore.blockbreak           |
| 設置           | st.ignore.blockplace           |
| アイテムを拾う      | st.ignore.itemdrop             |
| アイテムを捨てる     | st.ignore.itempickup           |
| 矢を拾う         | st.ignore.arrowpickup          |
| チェストを開ける     | st.ignore.inventory.chest      |
| エンダーチェストを開ける | st.ignore.inventory.enderchest |
| すべて          | st.ignore.*                    |

Permissionを管理するプラグインで()内の権限を与えてやると使えるようになります。  
初めてログインしてから一定時間経ったら権限を付与するというようなやり方などにご利用ください。  
(Permissionは増えたり、変わったり、なくなったりする可能性があります。)

# ビルド
Actionを使って自動ビルドするようにしてみました。  
[![Dev Build](https://github.com/plugin-yamagami2211/Survival-Tool/actions/workflows/maven.yml/badge.svg)](https://github.com/plugin-yamagami2211/Survival-Tool/actions/workflows/maven.yml)  
最新のビルドはAction->Dev Build #(数字が一番大きいやつ)->Artifactsの jar-file-in-the-zip の中に入っています。  
zipファイルの中にプラグインのjarが入っています。  
**Actionでビルドしたものは開発版ですので、不具合等がある可能性があります。**

# thanks
[複数のymlファイルを扱う](https://jyn.jp/bukkit-plugin-development-8/) @HimaJyun
