package com.java.cruisecompany.controller.action;

import com.java.cruisecompany.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The Action interface defines the contract for all actions that can be performed by the application.
 * All actions must implement this interface. The execute method is responsible for performing the specific action
 * requested by the user and returning a string that represents the URL of the page to be displayed next.
 */
public interface Action {
    /**
     * Executes the specific action requested by the user.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object containing the response data
     * @return a String representing the URL of the page to be displayed next.
     * @throws ServiceException if any error occurs while performing the action.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
