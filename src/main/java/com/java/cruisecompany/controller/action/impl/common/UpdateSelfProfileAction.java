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

/**
 * The UpdateSelfProfileAction class implements the Action interface
 * and represents the action that updates the user's profile with the provided parameters.
 */
public class UpdateSelfProfileAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    /**
     * Executes the action to update the user's profile.
     * <p>
     * It retrieves the current user from the HttpSession, validates the provided parameters,
     * and updates the user's information using the UserService instance. If an error occurs,
     * it adds the error message to the HttpSession and returns the user to the previous page.
     *
     * @param request  the HttpServletRequest object containing the request the client has made of the servlet
     * @param response the HttpServletResponse object containing the response the servlet sends to the client
     * @return the URL of the page to which the user should be redirected after the action is performed
     * @throws ServiceException if an error occurs while executing the action
     */
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
            request.getSession().setAttribute("errors", errors);
        }
        return request.getHeader("referer");
    }

    /**
     * Validates the provided parameters for updating the user's profile.
     *
     * @param login     the user's login
     * @param email     the user's email
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @return a Map of error messages, where the key is the name of the field that caused the error,
     * and the value is the error message to be displayed to the user
     */
    private Map<String, String> validateRouteParameters(String login, String email, String firstName, String lastName) {
        Map<String, String> errors = new HashMap<>();
        UserValidator.validateUserLogin(login, "update.user", errors);
        UserValidator.validateUserEmail(email, "update.user", errors);
        UserValidator.validateUserFirstName(firstName, "update.user", errors);
        UserValidator.validateUserLastName(lastName, "update.user", errors);
        return errors;
    }
}
