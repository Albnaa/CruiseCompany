package com.app.webapplication.model.dao;

import com.app.webapplication.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao <User> {
    Optional<User> findByLogin(String login);
}
