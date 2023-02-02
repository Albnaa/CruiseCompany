package com.java.cruisecompany.controller.action.impl.user.user;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.utils.validation.ShipValidator;
import com.java.cruisecompany.model.utils.validation.UserValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TopUpBalanceAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
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
            System.out.println(e.getMessage());
        }
        return request.getHeader("referer");
    }

    private Map<String, String> validateUserParameters(String amount) {
        Map<String, String> errors = new HashMap<>();
        UserValidator.validateTopUpAmount(amount, "topUp.user", errors);
        return errors;
    }
}
