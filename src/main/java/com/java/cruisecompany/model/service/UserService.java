package com.java.cruisecompany.model.service;

import com.java.cruisecompany.model.entity.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<UserDTO> {
    Optional<UserDTO> findByLoginAndPass(String login, String password);
    List<UserDTO> findByInitials(String initials);
    Optional<UserDTO> findByLogin(String login);
    List<UserDTO> findSorted(String query);
    long getNumOfRows(String query);
}
