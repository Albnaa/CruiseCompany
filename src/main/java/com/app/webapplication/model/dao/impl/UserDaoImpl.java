package com.app.webapplication.model.dao.impl;

import com.app.webapplication.model.dao.UserDao;
import com.app.webapplication.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public void create(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }
}
