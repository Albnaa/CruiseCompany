package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;

import java.util.List;
import java.util.Optional;

public interface EntityDAO<T> {
    void create(T entity) throws DAOException;
    void update(T entity) throws DAOException;
    void delete(long id) throws DAOException;
    Optional <T> findById(long id) throws DAOException;
    List<T> findAll() throws DAOException;
    List<T> findSorted(String query) throws DAOException;
    long getNumOfRows(String query) throws DAOException;
}
