package com.java.cruisecompany.model.service;

import com.java.cruisecompany.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User> {
    Optional<User> findByLoginAndPass(String login, String password);
    List<User> findByInitials(String initials);
}
