package com.java.cruisecompany.model.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    Optional<T> findById(int id);
    List<T> findAll();
}
