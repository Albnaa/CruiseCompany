package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * Action class that handles displaying the profile of the currently logged-in user.
 */
@Log4j2
public class ViewSelfProfileAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    /**
     * Executes the view self profile action by retrieving the user id from the session and using it to find the user in
     * the database, then setting the user as an attribute of the request.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the viewSelfProfile JSP page to display the user's profile
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = ((UserDTO) request.getSession().getAttribute("user")).getId();

        try {
            UserDTO user = userService.findById(userId).orElseThrow(NoSuchUserException::new);
            request.setAttribute("user", user);
        } catch (ServiceException e) {
            log.error("Error in view self profile action -> " + e.getMessage());
        }
        return "/WEB-INF/jsp/user/viewSelfProfile.jsp";
    }
}
