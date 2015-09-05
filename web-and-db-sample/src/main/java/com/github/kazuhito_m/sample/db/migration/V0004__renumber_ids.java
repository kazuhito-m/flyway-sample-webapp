package com.github.kazuhito_m.sample.db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * データの状態を見て「業務的・論理的に判断して更新」するようなマイグレーション。
 * (この例はちとかんたんすぎるが…)
 */
public class V0004__renumber_ids implements JdbcMigration {


    @Override
    public void migrate(Connection connection) throws Exception {

        connection.setAutoCommit(false);  //オートコミットはオフ

        // リナンバーしたい…が、そのまますると被るので、一度わけわからんくらい飛ばす。
        try (Statement statement = connection.createStatement()) {
            String sql = "UPDATE member SET member_id = member_id + 10000;";
            statement.executeUpdate(sql);
        }

        // 名前に「特定の文字」を含むものだけを前に、それ意外は名前順に整理。
        List<Integer> firstIds = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            List<Integer> seccondIds = new ArrayList<>();

            String sql = "SELECT member_id , kana FROM member ORDER BY kana";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                // 値取得。
                int id = rs.getInt("member_id");
                String kana = rs.getString("kana");
                // 特定の文字「イロフ」を含むなら…。
                if (kana.contains("イロフ")) {
                    firstIds.add(id);
                } else {
                    seccondIds.add(id);
                }
            }
            // 最初のIDリストに合成する。
            firstIds.addAll(seccondIds);
        }

        // IDの振り直し。
        int i = 1;
        String sql = "UPDATE member SET member_id = ? WHERE member_id = ?";
        for (Integer id : firstIds) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, i++);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }

        connection.commit();

    }
}
