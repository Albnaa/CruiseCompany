package com.java.cruisecompany.controller.action.impl.admin.user;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.repository.impl.UserDAOImpl;
import com.java.cruisecompany.model.service.UserService;
import com.java.cruisecompany.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateUserAction implements Action {
    UserService userService = AppContext.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        UserDTO user = UserDTO.builder()
                .id(Long.parseLong(request.getParameter("id")))
                .login(request.getParameter("login"))
                .email(request.getParameter("email"))
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .role(Role.getRoleFromString(request.getParameter("role")))
                .build();

        try {
            userService.update(user);
            request.getSession().removeAttribute("error");
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return request.getHeader("referer");
    }
}
