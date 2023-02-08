package com.java.cruisecompany.controller.action.impl.admin.user;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.utils.validation.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String role = request.getParameter("role");

        Map<String, String> errors = validateRouteParameters(id, login, email, firstName, lastName);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        UserDTO user = UserDTO.builder()
                .id(Long.parseLong(id))
                .login(login)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(Role.getRoleFromString(role))
                .build();

        try {
            userService.update(user);
            request.getSession().removeAttribute("error");
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return request.getHeader("referer");
    }

    private Map<String, String> validateRouteParameters(String id, String login, String email, String firstName, String lastName) {
        Map<String, String> errors = new HashMap<>();
        UserValidator.validateUserId(id, "update.user", errors);
        UserValidator.validateUserLogin(login, "update.user", errors);
        UserValidator.validateUserEmail(email, "update.user", errors);
        UserValidator.validateUserFirstName(firstName, "update.user", errors);
        UserValidator.validateUserLastName(lastName, "update.user", errors);
        return errors;
    }
}
