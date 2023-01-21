package com.java.cruisecompany.controller.action.impl.admin;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteUserAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {

        long userId = Long.parseLong(request.getParameter("userId"));
        userService.delete(userId);
        return "users.jsp";
    }
}
