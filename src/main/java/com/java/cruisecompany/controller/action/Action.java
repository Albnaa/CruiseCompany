package com.java.cruisecompany.controller.action;

import com.java.cruisecompany.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public interface Action {
    String execute(HttpServletRequest request) throws ServiceException;
}
