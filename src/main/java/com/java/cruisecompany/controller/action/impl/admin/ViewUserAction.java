package com.java.cruisecompany.controller.action.impl.admin;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.entity.UserDTO;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class ViewUserAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        Optional<UserDTO> user = userService.findById(userId);
        if (user.isPresent()) {
            request.setAttribute("user", user.get());
        } else {
            request.setAttribute("error", "Can`t find user with this id");
        }
        return "userProfile.jsp";
    }
}
