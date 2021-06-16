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
/st : プラグインのヘルプコマンド  
/st reload : Configをリロードする  
/home : 設定した場所にTPする。  
/home set: 現在座標を記録する(home用)  
/respawn : リスポーンポイントにTPする。  
/death : 直前に死亡したポイントにTPする

## 権限がないとできない系
Permissionがないと次のことができなくなります。  
(※最初からは使えないので**Configで有効**にする必要があります)
- チャット(st.ignore.chat)  
- 破壊(st.ignore.blockbreak)  
- 設置(st.ignore.blockplace)
- アイテムを拾う(st.ignore.itemdrop)
- アイテムを捨てる(st.ignore.itempickup)
- 矢を拾う(st.ignore.arrowpickup)
- チェストを開ける(st.ignore.inventory.chest)
- エンダーチェストを開ける(st.ignore.inventory.enderchest)
- すべて(st.ignore.*)

Permissionを管理するプラグインで()内の権限を与えてやると使えるようになります。  
初めてログインしてから一定時間経ったら権限を付与するというようなやり方などにご利用ください。  
(Permissionは増えたり、変わったり、なくなったりする可能性があります。)

# ビルド
Actionを使って自動ビルドするようにしてみました。  
[![Java CI with Maven](https://github.com/plugin-yamagami2211/Survival-Tool/actions/workflows/maven.yml/badge.svg)](https://github.com/plugin-yamagami2211/Survival-Tool/actions/workflows/maven.yml)  
最新のビルドはAction->Java CI with Maven #(数字が一番大きいやつ)->Artifactsの jar-file-in-the-zip の中に入っています。  
zipファイルの中にプラグインのjarが入っています。  
**Actionでビルドしたものは開発版ですので、不具合等がある可能性があります。**

# thanks
[複数のymlファイルを扱う](https://jyn.jp/bukkit-plugin-development-8/) @HimaJyun

