package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.model.connectionpool.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAO<T> {
    public static void executeNoReturn(String query, Object... args) {
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            setArgs(stmt, args);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional <T> executeOneReturn(String query, Object... args) {
        T entity = null;
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)){
            setArgs(stmt, args);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    entity = mapToEntity(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(entity);
    }

    public List<T> executeListReturn(String query, Object... args) {
        List<T> list = new ArrayList<>();
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            setArgs(stmt, args);
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    list.add(mapToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public long executeNumOfRowsReturn(String query) {
        long rows = 0;
        try (Connection con = DBManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)){
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    rows = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    private static void setArgs(PreparedStatement stmt, Object... args) throws SQLException {
        int k = 0;
        for (Object arg : args) {
            stmt.setObject(++k, arg);
        }
    }

    protected abstract T mapToEntity(ResultSet rs) throws SQLException;
}
