package com.java.cruisecompany.model.service.impl;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.ServiceException;
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
    public void create(UserDTO entity) throws ServiceException {
        try {
        userDAO.create(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(UserDTO entity) throws ServiceException {
        try {
        userDAO.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
        userDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserDTO> findById(int id) throws ServiceException {
        try {
        return userDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDTO> findAll() throws ServiceException {
        try {
        return userDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserDTO> findByLoginAndPass(String login, String password) throws ServiceException {
        try {
        return userDAO.findByLoginAndPass(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDTO> findByInitials(String initials) throws ServiceException {
        try {
        return userDAO.findByInitials(initials);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserDTO> findByLogin(String login) throws ServiceException {
        try {
        return userDAO.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDTO> findSorted(String query) throws ServiceException {
        try {
        return userDAO.findSorted(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long getNumOfRows(String query) throws ServiceException {
        try {
        return userDAO.getNumOfRows(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
