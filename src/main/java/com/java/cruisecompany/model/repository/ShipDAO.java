package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Ship;

import java.util.List;

public interface ShipDAO extends EntityDAO<Ship> {
    void updateImage(long id, String imagePath) throws DAOException;

    List<Ship> findSortedWithRoutes(String query) throws DAOException;

    long getNumOfRowsWithRoutes(String query) throws DAOException;

    void addRoute(long shipId, long routeId) throws DAOException;
    void deleteRoute(long shipId) throws DAOException;
}
