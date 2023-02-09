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

@Log4j2
public class TopUpBalanceAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
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

    private Map<String, String> validateUserParameters(String amount) {
        Map<String, String> errors = new HashMap<>();
        UserValidator.validateTopUpAmount(amount, "topUp.user", errors);
        return errors;
    }
}
