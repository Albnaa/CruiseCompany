package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.utils.validation.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.java.cruisecompany.model.utils.ExceptionUtil.remapMessage;

public class UpdateSelfProfileAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        Map<String, String> errors = validateRouteParameters(login, email, firstName, lastName);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .login(login)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(user.getRole())
                .build();

        try {
            userService.update(userDTO);
            request.getSession().removeAttribute("error");
        } catch (ServiceException e) {
            errors.put(remapMessage(e.getMessage(), "update.user"), e.getMessage());
            request.getSession().setAttribute("error", errors);
        }
        return request.getHeader("referer");
    }

    private Map<String, String> validateRouteParameters(String login, String email, String firstName, String lastName) {
        Map<String, String> errors = new HashMap<>();
        UserValidator.validateUserLogin(login, "update.user", errors);
        UserValidator.validateUserEmail(email, "update.user", errors);
        UserValidator.validateUserFirstName(firstName, "update.user", errors);
        UserValidator.validateUserLastName(lastName, "update.user", errors);
        return errors;
    }
}
