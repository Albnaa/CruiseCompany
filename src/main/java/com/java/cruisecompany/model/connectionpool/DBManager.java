package com.java.cruisecompany.model.connectionpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The DBManager class provides a singleton instance for accessing a database connection pool using the HikariCP library.
 */
public class DBManager {

    private static final HikariConfig config = new HikariConfig("/db.properties");
    /**
     * The Hikari data source created from the configuration.
     */
    private static final HikariDataSource ds;

    static {
        ds = new HikariDataSource(config);
    }

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private DBManager() {
    }

    /**
     * Returns a connection from the connection pool.
     *
     * @return a database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
