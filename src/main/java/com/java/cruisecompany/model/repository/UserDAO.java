package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDAO extends EntityDAO<User> {
    void topUpBalance(long userId, BigDecimal amount) throws DAOException;

    Optional<User> findByLoginAndPass(String login, String password) throws DAOException;
    Optional<User> findByLogin(String Login) throws DAOException;
    List<User> findByInitials(String initials) throws DAOException;
    void deductFromBalance(long userId, BigDecimal amount) throws DAOException;
}
