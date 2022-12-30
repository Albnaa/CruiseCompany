package com.app.webapplication.model.repository;

import com.app.webapplication.model.entity.User;

import java.util.Optional;

public interface UserDAO extends EntityDAO<User> {
    Optional<User> findByLogin(String login);
}
