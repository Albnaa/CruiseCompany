package com.java.cruisecompany.controller.action.impl.admin.user;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

/**
 * The ViewUserAction class is an implementation of the Action interface
 * <p>
 * that handles requests to view a user's profile.
 */
public class ViewUserAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    /**
     * Executes the action to view a user
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return a string representing the URL of the next page to display after the action is executed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Optional<UserDTO> user = userService.findById(userId);
        if (user.isPresent()) {
            request.setAttribute("user", user.get());
        } else {
            request.setAttribute("error", "Can`t find user with this id");
        }
        return "/WEB-INF/jsp/user/viewProfile.jsp";
    }
}
