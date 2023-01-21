package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.entity.UserDTO;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;

public class SignUpAction implements Action {
    UserService userService = new UserServiceImpl(new UserDAOImpl());
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        if (!Objects.equals(password, confirmPassword)) {
            request.getSession().setAttribute("error", "Password's don't match!");
            return "signUp.jsp";
        } else if (userService.findByLogin(login).isPresent()) {
            request.getSession().setAttribute("error", "User with such username already exists");
            return "signUp.jsp";
        }

        UserDTO user = UserDTO.builder()
                .login(login)
                .email(email)
                .password(password)
                .firstName(firstname)
                .lastName(lastname)
                .build();

        userService.create(user);
        return "login.jsp";
    }
}
