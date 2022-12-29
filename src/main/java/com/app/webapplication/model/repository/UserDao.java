package com.app.webapplication.model.repository;

import com.app.webapplication.model.entity.User;

import java.util.Optional;

public interface UserDao extends EntityDao<User> {
    Optional<User> findByLogin(String login);
}
