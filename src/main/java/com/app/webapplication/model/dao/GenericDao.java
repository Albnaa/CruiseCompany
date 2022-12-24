package com.app.webapplication.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    Optional <T> findById(int id);
    List<T> findAll();
}
