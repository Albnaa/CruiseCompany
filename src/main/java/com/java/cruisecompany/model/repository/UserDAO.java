package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends EntityDAO<UserDTO> {
    Optional<UserDTO> findByLoginAndPass(String login, String password) throws DAOException;
    Optional<UserDTO> findByLogin(String Login) throws DAOException;
    List<UserDTO> findByInitials(String initials) throws DAOException;
    List<UserDTO> findSorted(String queryParameters) throws DAOException;
    long getNumOfRows(String query) throws DAOException;
}
