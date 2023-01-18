package com.java.cruisecompany.model.repository.impl;

import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.repository.GenericDAO;
import com.java.cruisecompany.model.repository.UserDAO;
import com.java.cruisecompany.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends GenericDAO<User> implements UserDAO {
    private final static String CREATE_USER = "INSERT INTO user (login, email, password, first_name," +
            " last_name, Role_id) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_USER = "UPDATE user SET login = ?, email = ?, " +
            "first_name = ?, last_name = ?, role_id = ? WHERE id = ?";
    private final static String DELETE_USER = "DELETE FROM user WHERE id = ?";
    private final static String SELECT_ALL = "SELECT * FROM user";
    private final static String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";
    private final static String SELECT_BY_LOGIN = SELECT_ALL + " WHERE login = ?";
    private final static String SELECT_BY_LOGIN_AND_PASSWORD = SELECT_ALL + " WHERE login = ? AND password = ?";
    private final static String SELECT_BY_INITIALS = SELECT_ALL + " WHERE first_name = ? OR last_name = ?";


    @Override
    public void create(User entity) {
        executeNoReturn(CREATE_USER, entity.getLogin(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getFirstName(),
                entity.getLastName(),
                2);
    }

    @Override
    public void update(User entity) {
        executeNoReturn(UPDATE_USER,
                entity.getLogin(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getRole().getIndex(),
                entity.getId());
    }

    @Override
    public void delete(long id) {
        executeNoReturn(DELETE_USER, id);
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
    public Optional<User> findByLoginAndPass(String login, String password) {
        return executeOneReturn(SELECT_BY_LOGIN_AND_PASSWORD , login, password);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return executeOneReturn(SELECT_BY_LOGIN, login);
    }

    @Override
    public List<User> findByInitials(String initials) {
        return executeListReturn(SELECT_BY_INITIALS, initials, initials);
    }

    @Override
    protected User mapToEntity(ResultSet rs) throws SQLException {
        int k = 0;
        return User.builder()
                .id(rs.getInt(++k))
                .login(rs.getString(++k))
                .email(rs.getString(++k))
                .password(rs.getString(++k))
                .firstName(rs.getString(++k))
                .lastName(rs.getString(++k))
                .role(Role.getRole(rs.getInt(++k)))
                .balance(rs.getDouble(++k))
                .build();
    }

}
