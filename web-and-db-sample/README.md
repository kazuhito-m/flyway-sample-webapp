web-and-db-sample
==============

## What's this ?

このプロジェクトは「なんの変哲もないJavaのWeb(war)アプリケーションのプロジェクトでFlywayを導入するには」という例です。

## Usage

このプロジェクトでは「Flywayでマイグレーションを行う３つの方法」の実例をデモを提供します。

以下の操作を行って下さい。




## 前準備

上記のマイグレーション操作は、自身ローカル環境にて実行することを前提とします。

### コンソールからのflywayコマンドでのマイグレーション

TODO

### Mavenプラグインを使ってのマイグレーション

TODO

### デプロイすることによる自動マイグレーション

TODO

### DBの用意

サンプルは

+ MySQLのサーバがインストールされていること
+ MySQLのDB内に"sample"(user:sample,pass:sampleps)という名のDatabasが作成されていること

MySQLをインストールしておいて下さい。(バージョンは、どれでも動くと思いますができるだけ新しいもので)

筆者環境はUbuntu Linuxなので、以下のコマンドでインストールします。

```bash
sudo apt-get install mysql-server # 対話型でrootユーザ用のパスワードが求められますが、任意かつ覚えておいて下さい。
```

次に、localhostでアクセス出来るように空のDatabaseを作成してください。

```bash
mysql -u root -p # パスワードが求められるので、インストール時に指定したrootのパスワードを
# 以下 mysql> な コンソールから操作。
CREATE DATABASE IF NOT EXISTS sample;
GRANT ALL PRIVILEGES ON *.* TO sample@"localhost" IDENTIFIED BY "sampleps";
exit;

# 先ほど作成したDBとユーザでログイン。
mysql -u sample -p sample
# 以下 mysql> な コンソールから操作。
show tables;
Empty set (0.00 sec)
# OK
```

### Webコンテナサーバの用意

Warファイルをデプロイ出来るJavaのWebコンテナサーバをインストールしてください。

動作確認したのはUbuntu Linuxでのtomcat8ですので、その手順を示しておきます。

```bash
sudo apt-get install tomcat8
# インストールできたら、http://localhost:8080/ にてサンプルページを表示し立ち上がりを確認。
```

## Author

Kazuhito Miura ( [@kazuhito_m](https://twitter.com/kazuhito_m "kazuhito_m on Twitter") )
