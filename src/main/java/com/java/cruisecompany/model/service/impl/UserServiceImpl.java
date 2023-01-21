package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.model.entity.UserDTO;
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
    public void create(UserDTO entity) {
        userDAO.create(entity);
    }

    @Override
    public void update(UserDTO entity) {
        userDAO.update(entity);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    public Optional<UserDTO> findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<UserDTO> findByLoginAndPass(String login, String password) {
        return userDAO.findByLoginAndPass(login, password);
    }

    @Override
    public List<UserDTO> findByInitials(String initials) {
        return userDAO.findByInitials(initials);
    }

    @Override
    public Optional<UserDTO> findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    public List<UserDTO> findSorted(String query) {
        return userDAO.findSorted(query);
    }

    @Override
    public long getNumOfRows(String query) {
        return userDAO.getNumOfRows(query);
    }

}
