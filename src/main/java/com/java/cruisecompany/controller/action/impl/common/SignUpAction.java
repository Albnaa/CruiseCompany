package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.InvalidInputException;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

public class SignUpAction implements Action {
    UserService userService = new UserServiceImpl(new UserDAOImpl());

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        request.getSession().removeAttribute("user");

        UserDTO user = UserDTO.builder()
                .login(request.getParameter("login"))
                .email(request.getParameter("email"))
                .firstName(request.getParameter("firstname"))
                .lastName(request.getParameter("lastname"))
                .build();

        try {
            userService.register(user, request.getParameter("password"), request.getParameter("confirm-password"));
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());

            request.getSession().setAttribute("user", user);

            return "signUp.jsp";
        }

        request.getSession().removeAttribute("error");
        return "login.jsp";
    }
}
