package com.app.webapplication.model.repository.impl;

import com.app.webapplication.model.repository.GenericDAO;
import com.app.webapplication.model.repository.UserDAO;
import com.app.webapplication.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends GenericDAO<User> implements UserDAO {
    private final static String CREATE_USER = "INSERT INTO user (login, email, password, first_name," +
            " last_name, Role_id) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_USER = "UPDATE user SET login = ?, email = ?, password = ?, " +
            "first_name = ?, last_name = ? WHERE id = ?";
    private final static String DELETE_USER = "DELETE FROM user WHERE id = ?";
    private final static String SELECT_ALL = "SELECT * FROM user";
    private final static String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";
    private final static String SELECT_BY_LOGIN = SELECT_ALL + " WHERE login = ?";


    @Override
    public void create(User entity) {
        executeNoReturn(CREATE_USER, entity.getLogin(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getFirst_name(),
                entity.getLast_name(),
                entity.getRoleId());
    }

    @Override
    public void update(User entity) {
        executeNoReturn(UPDATE_USER, entity.getId());
    }

    @Override
    public void delete(User entity) {
        executeNoReturn(DELETE_USER, entity.getId());
    }

    @Override
    public Optional<User> findById(int id) {
        return executeOneReturn(SELECT_BY_ID, id);
    }

    @Override
    public List<User> findAll() {
        return executeListReturn(SELECT_ALL);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return executeOneReturn(SELECT_BY_LOGIN, login);
    }

    @Override
    protected User mapToEntity(ResultSet rs) throws SQLException {
        int k = 0;
        return User.builder()
                .id(rs.getInt(++k))
                .login(rs.getString(++k))
                .email(rs.getString(++k))
                .password(rs.getString(++k))
                .first_name(rs.getString(++k))
                .last_name(rs.getString(++k))
                .roleId(rs.getInt(++k))
                .build();
    }
}
