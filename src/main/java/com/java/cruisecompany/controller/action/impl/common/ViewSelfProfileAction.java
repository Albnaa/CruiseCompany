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
 * An implementation of the Action interface that handles displaying the
 * profile of the currently logged-in user.
 */
@Log4j2
public class ViewSelfProfileAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    /**
     * Executes the action to display the profile of the currently logged-in user.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP page to display the user's profile
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
