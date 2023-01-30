package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.NoSuchUserException;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class ViewSelfProfileAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        long userId = ((UserDTO)request.getSession().getAttribute("user")).getId();

        try {
            UserDTO user = userService.findById(userId).orElseThrow(NoSuchUserException::new);
            request.setAttribute("user", user);
        } catch (ServiceException | NoSuchUserException e) {
            System.out.println(e.getMessage());
        }
        return "/WEB-INF/jsp/user/viewSelfProfile.jsp";
    }
}
