package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends EntityDAO<User> {
    Optional<User> findByLoginAndPass(String login, String password) throws DAOException;
    Optional<User> findByLogin(String Login) throws DAOException;
    List<User> findByInitials(String initials) throws DAOException;
    List<User> findSorted(String queryParameters) throws DAOException;
}
