package com.java.cruisecompany.controller.action.impl.admin.user;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * An action class that handles deleting a user from the system.
 */
public class DeleteUserAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    /**
     * Executes the action to delete a user from the system.
     *
     * @param request  the HTTP servlet request containing the user ID of the user to delete
     * @param response the HTTP servlet response
     * @return a string representing the URL of the next page to display after the action is executed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = Long.parseLong(request.getParameter("userId"));
        try {
            userService.delete(userId);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return "controller?action=manage_users";
    }
}
