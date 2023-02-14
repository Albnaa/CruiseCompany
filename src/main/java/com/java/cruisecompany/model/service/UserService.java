package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService extends Service<UserDTO> {
    void register(UserDTO userDTO, String password) throws ServiceException;
    Optional<UserDTO> findByLoginAndPass(String login, String password) throws ServiceException;
    void topUpBalance(long userId, BigDecimal amount) throws ServiceException;
    List<UserDTO> findByInitials(String initials) throws ServiceException;
    Optional<UserDTO> findByLogin(String login) throws ServiceException;
    long getNumOfRows(String query) throws ServiceException;
}
