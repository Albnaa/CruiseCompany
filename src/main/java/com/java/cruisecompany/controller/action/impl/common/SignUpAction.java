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

import java.util.ArrayList;
import java.util.List;
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

        UserDTO user = UserDTO.builder()
                .login(login)
                .email(email)
                .firstName(firstname)
                .lastName(lastname)
                .build();

//        try {
//            userService.register(user, password, confirmPassword);
//        } catch (ServiceException e) {
//            request.getSession().setAttribute("error", e.getMessage());
//            return "signUp.jsp";
//        }
        List<String> errors = new ArrayList<>();
        errors.add("error.signUp.login");
        errors.add("error.signUp.loginExists");
        errors.add("error.signUp.email");
        errors.add("error.signUp.emailExists");
        errors.add("error.signUp.password");
        errors.add("error.signUp.confirmPassword");
        errors.add("error.signUp.firstName");
        errors.add("error.signUp.lastName");

        request.getSession().setAttribute("errors", errors);
        return "signUp.jsp";

//        return "login.jsp";
    }
}
