package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.DAOException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.entity.Ship;

public interface ShipService extends Service<ShipDTO>{
    void addRoute(long shipId, long routeId) throws ServiceException;
    void deleteRoute(long shipId) throws ServiceException;
}
