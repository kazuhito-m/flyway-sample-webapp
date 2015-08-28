#!/bin/bash

# flyway のマイグレーションを行う際の「下準備」と
# 実際の"flyway"コマンドの実行を一度に行うスクリプト。
# Linuxかつbash専用。

# このスクリプトが居るpathを割り出し
script_path=`dirname ${0}`

# maven / デプロイ用のFlywayマイグレーションSQLのフォルダから、
# SQLをコマンドライン用のフォルダにコピーしてくる。
rm -rf ${script_path}/sql/*
cp ${script_path}/../src/main/resources/com/github/kazuhito_m/sample/db/migration/* ./sql/

# flyway 実行
cd ${script_path}
./flyway -X migrate
