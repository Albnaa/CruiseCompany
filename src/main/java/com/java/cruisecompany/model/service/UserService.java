package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.entity.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<UserDTO> {
    Optional<UserDTO> findByLoginAndPass(String login, String password) throws ServiceException;
    List<UserDTO> findByInitials(String initials) throws ServiceException;
    Optional<UserDTO> findByLogin(String login) throws ServiceException;
    List<UserDTO> findSorted(String query) throws ServiceException;
    long getNumOfRows(String query) throws ServiceException;
}
