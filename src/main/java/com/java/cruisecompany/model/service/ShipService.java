package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;

import java.util.List;

public interface ShipService extends Service<ShipDTO>{
    List<ShipDTO> findSortedWithRoutes(String query) throws ServiceException;

    void addRoute(long shipId, long routeId) throws ServiceException;
    void deleteRoute(long shipId) throws ServiceException;
}
