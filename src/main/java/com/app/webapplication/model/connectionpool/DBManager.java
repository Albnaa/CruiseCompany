package com.app.webapplication.model.connectionpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static final HikariConfig config = new HikariConfig("/db.properties");
    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource(config);
    }

    private DBManager() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
