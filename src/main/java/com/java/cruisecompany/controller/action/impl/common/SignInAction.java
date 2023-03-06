package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.dao.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import com.java.cruisecompany.model.utils.ExceptionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * An implementation of the {@link Action} interface that handles user sign-in.
 * <p>
 * It retrieves user login credentials from the request, verifies the user's credentials,
 * and sets the session attributes to store the logged-in user's information.
 */
public class SignInAction implements Action {
    UserService userService = new UserServiceImpl(new UserDAOImpl());

    /**
     * This implementation retrieves the user's login credentials from the request,
     * verifies the user's credentials using the {@link UserService} implementation,
     * sets session attributes to store the logged-in user's information, and returns
     * a String indicating the next action to take.</p>
     * <p>If an error occurs, the method sets an error message in the session and returns
     * the URL of the previous page to allow the user to correct the error.</p>
     *
     * @param request  the servlet request
     * @param response the servlet response
     * @return a String representing the URL of the next action to take
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        session.removeAttribute("errors");

        Map<String, String> errors = new HashMap<>();
        Optional<UserDTO> user;

        try {
            user = userService.findByLoginAndPass(login, password);
        } catch (ServiceException e) {
            errors.put("error.signIn.user", ExceptionUtil.getRootMessage(e));
            session.setAttribute("errors", errors);
            return request.getHeader("referer");
        }

        session.setAttribute("user", user.get());
        session.setAttribute("role", user.get().getRole());
        if (Objects.requireNonNull(user.get().getRole()) == Role.ADMIN) {
            return "controller?action=manage_users";
        }
        return "controller?action=manage_catalog&rows=6";
    }
}
