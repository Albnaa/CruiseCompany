package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends EntityDAO<User> {
    Optional<User> findByLoginAndPass(String login, String password);
    List<User> findByInitials(String initials);
}
