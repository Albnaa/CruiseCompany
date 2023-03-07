package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Action class that handles user sign out and user session invalidation.
 */
public class SignOutAction implements Action {
    /**
     * Executes the logout action by invalidating the current session and redirecting to the login page.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL that represents the path to the login page
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String locale = (String) session.getAttribute("locale");
            session.invalidate();
            request.getSession().setAttribute("locale", locale);
        }
        return "login.jsp";
    }
}
