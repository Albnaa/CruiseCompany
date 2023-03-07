package com.java.cruisecompany.controller.action.impl.user.user;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.utils.validation.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Action class that handles user balance top-up functionality.
 */
@Log4j2
public class TopUpBalanceAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    /**
     * Executes the top-up balance action by getting the user ID from the session, validating the amount parameter,
     * topping up the user's balance by the specified amount, updating the user's information in the session, and returning
     * the user to the previous page.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long id = ((UserDTO) request.getSession().getAttribute("user")).getId();
        String amount = request.getParameter("amount");

        Map<String, String> errors = validateUserParameters(amount);
        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        try {
            userService.topUpBalance(id, BigDecimal.valueOf(Double.parseDouble(amount)));
            UserDTO user = userService.findById(id).orElseThrow(NoSuchUserException::new);
            request.getSession().setAttribute("user", user);
        } catch (Exception e) {
            log.error("Error in top up balance action -> " + e.getMessage());
        }
        return request.getHeader("referer");
    }

    /**
     * Validates the user input amount parameter.
     *
     * @param amount the amount of money to validate
     * @return a Map containing any errors that were found during validation
     */
    private Map<String, String> validateUserParameters(String amount) {
        Map<String, String> errors = new HashMap<>();
        UserValidator.validateTopUpAmount(amount, "topUp.user", errors);
        return errors;
    }
}
