package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.dao.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import com.java.cruisecompany.model.utils.validation.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the Action interface to handle user sign-up requests.
 * <p>
 * It validates user parameters, creates a UserDTO object and passes it to UserServiceImpl to create a new user in
 * the database.
 * <p>
 * It returns the appropriate JSP page based on the result of the operation.
 */
@Log4j2
public class SignUpAction implements Action {
    UserService userService = new UserServiceImpl(new UserDAOImpl());

    /**
     * Executes the sign-up action by getting user parameters from the request, validating them, creating a new user,
     * and returning the appropriate JSP page.
     *
     * @param request  the HttpServletRequest object containing user parameters
     * @param response the HttpServletResponse object for returning the response
     * @return a String representing the JSP page to display after the sign-up action is executed
     */
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
            userService.register(user, password);
        } catch (ServiceException e) {
            log.error("Error in sign Up Action" + e.getMessage());
            request.getSession().setAttribute("error", e.getMessage());
            request.getSession().setAttribute("user", user);
            return "signUp.jsp";
        }

        request.getSession().removeAttribute("error");
        return "login.jsp";
    }

    /**
     * Validates user parameters and returns a map of error messages for any invalid parameters.
     *
     * @param login           the user login to validate
     * @param email           the user email to validate
     * @param firstName       the user first name to validate
     * @param lastName        the user last name to validate
     * @param password        the user password to validate
     * @param confirmPassword the user confirm password to validate
     * @return a map of error messages for any invalid parameters
     */
    private Map<String, String> validateUserParameters(String login, String email, String firstName, String lastName, String password, String confirmPassword) {
        Map<String, String> errors = new HashMap<>();
        UserValidator.validateUserLogin(login, "signUp.user", errors);
        UserValidator.validateUserEmail(email, "signUp.user", errors);
        UserValidator.validateUserFirstName(firstName, "signUp.user", errors);
        UserValidator.validateUserLastName(lastName, "signUp.user", errors);
        UserValidator.validateUserPassword(password, "signUp.user", errors);
        UserValidator.confirmUserPassword(password, confirmPassword, "signUp.user", errors);
        return errors;
    }
}
