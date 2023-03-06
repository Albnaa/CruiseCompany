package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * SignOutAction is an implementation of the Action interface.
 * <p>
 * This action is used to perform user sign out and invalidate user session.
 */
public class SignOutAction implements Action {
    /**
     * This method is used to handle user sign out and invalidate user session.
     *
     * @param request  the servlet request
     * @param response the servlet response
     * @return a String that represents the path to the login page
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
