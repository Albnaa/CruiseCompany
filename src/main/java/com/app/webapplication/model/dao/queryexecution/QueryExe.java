package com.app.webapplication.model.dao.queryexecution;

import com.app.webapplication.model.connectionpool.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class QueryExe<T> {
    public static void executeUpdate(String query, Object... args) {
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            int k = 0;
            for (Object arg : args) {
                stmt.setObject(++k, arg);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional <T> executeQueryEntity(String query, Object... args) {
        T entity = null;
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)){
            int k = 0;
            for (Object arg : args) {
                stmt.setObject(++k, arg);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                entity = mapToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(entity);
    }

    protected abstract T mapToEntity(ResultSet set) throws SQLException;
}
