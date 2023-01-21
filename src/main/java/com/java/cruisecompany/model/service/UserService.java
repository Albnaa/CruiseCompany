package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<UserDTO> {
    void register(UserDTO userDTO, String password) throws ServiceException;

    Optional<UserDTO> findByLoginAndPass(String login, String password) throws ServiceException;
    List<UserDTO> findByInitials(String initials) throws ServiceException;
    Optional<UserDTO> findByLogin(String login) throws ServiceException;
    List<UserDTO> findSorted(String query) throws ServiceException;
    long getNumOfRows(String query) throws ServiceException;
}
