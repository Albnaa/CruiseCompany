package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.PortDAO;
import com.java.cruisecompany.model.entity.Port;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PortDAOImpl extends GenericDAO<Port> implements PortDAO {
    private final static String INSERT_PORT = "INSERT INTO port (name) VALUES (?)";
    private final static String UPDATE_PORT = "UPDATE port SET name = ? WHERE id = ?";
    private final static String DELETE_PORT = "DELETE FROM port WHERE id = ?";
    private final static String SELECT_BY_ID = "SELECT * FROM port WHERE id = ?";
    private final static String SELECT_ALL = "SELECT * FROM port";

    @Override
    public void create(Port entity) {
        executeNoReturn(INSERT_PORT, entity.getName());
    }

    @Override
    public void update(Port entity) {
        executeNoReturn(UPDATE_PORT, entity.getName(), entity.getId());
    }

    @Override
    public void delete(long id) {
        executeNoReturn(DELETE_PORT, id);
    }

    @Override
    public Optional<Port> findById(int id) {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    @Override
    public List<Port> findAll() {
        return executeListReturn(SELECT_ALL);
    }

    @Override
    public Port mapToEntity(ResultSet rs) throws SQLException {
        int k = 0;
        return Port.builder()
                .id(rs.getInt(++k))
                .name(rs.getString(++k))
                .build();
    }
}
