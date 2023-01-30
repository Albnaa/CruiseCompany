package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Port;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.PortDAO;

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
    private final static String SELECT_COUNT_OF_ROWS = "SELECT COUNT(*) FROM port";

    @Override
    public void create(Port entity) throws DAOException {
        executeNoReturn(INSERT_PORT, entity.getName());
    }

    @Override
    public void update(Port entity) throws DAOException {
        executeNoReturn(UPDATE_PORT, entity.getName(), entity.getId());
    }

    @Override
    public void delete(long id) throws DAOException {
        executeNoReturn(DELETE_PORT, id);
    }

    @Override
    public Optional<Port> findById(long id) throws DAOException {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    @Override
    public List<Port> findAll() throws DAOException {
        return executeListReturn(SELECT_ALL);
    }

    @Override
    public List<Port> findSorted(String query) throws DAOException {
        return executeListReturn(SELECT_ALL + query);
    }


    @Override
    public long getNumOfRows(String query) throws DAOException {
        return executeNumOfRowsReturn(SELECT_COUNT_OF_ROWS + query);
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
