package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import com.java.cruisecompany.model.utils.validation.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class SignUpAction implements Action {
    UserService userService = new UserServiceImpl(new UserDAOImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        Map<String, String> errors = validateUserParameters(login, email, firstName, lastName, password, confirmPassword);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        UserDTO user = UserDTO.builder()
                .login(login)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        try {
            userService.register(user, password, confirmPassword);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
            request.getSession().setAttribute("user", user);
            return "signUp.jsp";
        }

        request.getSession().removeAttribute("error");
        return "login.jsp";
    }

    private Map<String, String> validateUserParameters(String login, String email, String firstName, String lastName, String password, String confirmPassword) {
        Map<String, String> errors = new HashMap<>();
        UserValidator.validateUserLogin(login, "signUp.user", errors);
        UserValidator.validateUserEmail(email, "signUp.user", errors);
        UserValidator.validateUserFirstName(firstName,  "signUp.user", errors);
        UserValidator.validateUserLastName(lastName,  "signUp.user", errors);
        UserValidator.validateUserPassword(password, "signUp.user", errors);
        UserValidator.confirmUserPassword(password, confirmPassword, "signUp.user", errors);
        return errors;
    }
}
