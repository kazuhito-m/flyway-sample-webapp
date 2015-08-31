package com.github.kazuhito_m.sample.db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.Statement;

/**
 * データの状態を見て「業務的・論理的に判断して更新」するようなマイグレーション。
 * (この例はちとかんたんすぎるが…)
 */
public class V0004__renumber_ids implements JdbcMigration {


    @Override
    public void migrate(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement();) {

            if ( statement.execute(sql) ) {

            }

            return statement.getResultSet();



            statement.executeUpdate("INSERT INTO HOGE VALUES (1, 'hoge')");
        }
    }
}
