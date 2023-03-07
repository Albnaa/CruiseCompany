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
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

import static com.java.cruisecompany.model.utils.ExceptionUtil.remapMessage;

/**
 * An action class that handles updating a user
 */
@Log4j2
public class UpdateUserAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    /**
     * Executes the update user action by updating the user details with the given parameters from request.
     * <p>
     * If the request parameters fail validation, sets the errors in the session and redirects to the previous page.
     * If the update operation throws a ServiceException, sets the error in the session and redirects to the previous page.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
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
                .role(Role.parse(role))
                .build();

        try {
            userService.update(user);
            request.getSession().removeAttribute("error");
        } catch (ServiceException e) {
            log.error("Error in update user action -> " + e.getMessage());

            errors.put(remapMessage(e.getMessage(), "update.user"), e.getMessage());
            request.getSession().setAttribute("errors", errors);
        }
        return request.getHeader("referer");
    }

    /**
     * Validates the parameters of the user to be updated.
     *
     * @param id        the id of the user to be updated
     * @param login     the login of the user to be updated
     * @param email     the email of the user to be updated
     * @param firstName the first name of the user to be updated
     * @param lastName  the last name of the user to be updated
     * @return a map of error messages for any invalid parameters
     */
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
