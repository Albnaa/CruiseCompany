package com.java.cruisecompany.model.repository;

import java.util.List;
import java.util.Optional;

public interface EntityDAO<T> {
    void create(T entity);
    void update(T entity);
    void delete(long id);
    Optional <T> findById(int id);
    List<T> findAll();
}
