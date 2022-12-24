package com.app.webapplication.model.dao.impl;

import com.app.webapplication.model.dao.PortDao;
import com.app.webapplication.model.dao.queryexecution.QueryExe;
import com.app.webapplication.model.dao.queryexecution.mapper.PortMapper;
import com.app.webapplication.model.entity.Port;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.app.webapplication.model.dao.queryexecution.QueryExe.executeUpdate;

public class PortDaoImpl implements PortDao {
    QueryExe <Port> queryExe = new PortMapper();

    @Override
    public void create(Port entity) {
        executeUpdate("INSERT INTO port (name) VALUES (?)", entity.getName());
    }

    @Override
    public void update(Port entity) {
        executeUpdate("UPDATE port SET name = ? WHERE id = ?", entity.getName(), entity.getId());
    }

    @Override
    public void delete(Port entity) {
        executeUpdate("DELETE FROM port WHERE id = ?", entity.getId());
    }

    @Override
    public Optional<Port> findById(int id) {
        return queryExe.executeQueryEntity("SELECT * FROM port WHERE id = ?", id);
    }

    @Override
    public List<Port> findAll() {
        return null;
    }

    public static void main(String[] args) {
        PortDaoImpl portDao = new PortDaoImpl();
        System.out.println(portDao.findById(5));
    }
}
