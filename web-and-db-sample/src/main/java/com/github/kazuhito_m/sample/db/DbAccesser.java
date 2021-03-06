package com.github.kazuhito_m.sample.db;

import com.github.kazuhito_m.sample.config.Settings;

import java.sql.*;

public class DbAccesser {

    private String driver;
    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    /**
     * 引数なしのコンストラクタ
     * 既定値を使用する
     */
    public DbAccesser() {

        Settings settings = Settings.get();

        driver = settings.getJdbcDriver();
        url = settings.getJdbcUrl();
        user = settings.getJdbcUser();
        password = settings.getJdbcPassword();
    }

    /**
     * データベースへの接続を行う
     */
    public synchronized void open() throws Exception {
        connection = this.getConnection();
        statement = connection.createStatement();
    }

    /**
     * 別途実験のため、コネクション取得の部分のみ切り離す。
     * @return 接続済みコネクション。
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * SQL 文を実行した結果の ResultSet を返す
     * @param sql SQL 文
     */
    public ResultSet getResultSet(String sql) throws Exception {
        if ( statement.execute(sql) ) {
            return statement.getResultSet();
        }
        return null;
    }

    /**
     * SQL 文の実行
     * @param sql SQL 文
     */
    public void execute(String sql) throws Exception {
        statement.execute(sql);
    }

    /**
     * データベースへのコネクションのクローズ
     */
    public synchronized void close() throws Exception {
        if ( resultset != null ) resultset.close();
        if ( statement != null ) statement.close();
        if ( connection != null ) connection.close();
    }

}
