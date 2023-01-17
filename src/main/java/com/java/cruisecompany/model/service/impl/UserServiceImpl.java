package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.repository.UserDAO;
import com.java.cruisecompany.model.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public void create(User entity) {
        userDAO.create(entity);
    }

    @Override
    public void update(User entity) {
        userDAO.update(entity);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    public Optional<User> findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> findByLoginAndPass(String login, String password) {
        return userDAO.findByLoginAndPass(login, password);
    }

    @Override
    public List<User> findByInitials(String initials) {
        return userDAO.findByInitials(initials);
    }
}
