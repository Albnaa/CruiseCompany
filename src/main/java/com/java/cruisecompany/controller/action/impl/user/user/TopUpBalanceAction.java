package com.java.cruisecompany.controller.action.impl.user.user;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.User;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

public class TopUpBalanceAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        long userId = ((UserDTO) request.getSession().getAttribute("user")).getId();
        try {
            userService.topUpBalance(userId, BigDecimal.valueOf(Double.parseDouble(request.getParameter("amount"))));
            UserDTO user = userService.findById(userId).orElseThrow(NoSuchUserException::new);
            request.getSession().setAttribute("user", user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return request.getHeader("referer");
    }
}
