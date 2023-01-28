package com.java.cruisecompany.model.repository;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.model.entity.Ship;

public interface ShipDAO extends EntityDAO<Ship> {
    void addRoute(long shipId, long routeId) throws DAOException;
    void deleteRoute(long shipId) throws DAOException;
}
