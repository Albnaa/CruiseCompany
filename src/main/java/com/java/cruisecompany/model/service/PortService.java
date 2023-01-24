package com.java.cruisecompany.model.service;

import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.PortDTO;
public interface PortService extends Service<PortDTO>{

    long getNumOfRows(String query) throws ServiceException;
}
