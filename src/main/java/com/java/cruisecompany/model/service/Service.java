package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    void create(T entity) throws ServiceException;
    void update(T entity) throws ServiceException;
    void delete(long id) throws ServiceException;
    Optional<T> findById(long id) throws ServiceException;
    List<T> findAll() throws ServiceException;
    List<T> findSorted(String query) throws ServiceException;
    long getNumOfRows(String query) throws ServiceException;
}
