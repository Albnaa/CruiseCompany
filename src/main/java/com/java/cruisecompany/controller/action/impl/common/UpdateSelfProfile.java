package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.UserDTO;
import com.java.cruisecompany.model.entity.enums.Role;
import com.java.cruisecompany.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

public class UpdateSelfProfile implements Action {
    UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        UserDTO userDTO = UserDTO.builder()
                .id(((UserDTO) request.getSession().getAttribute("user")).getId())
                .login(request.getParameter("login"))
                .email(request.getParameter("email"))
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .balance(BigDecimal.valueOf(Double.parseDouble(request.getParameter("balance"))))
                .role(Role.valueOf(request.getParameter("role")))
                .build();

        try {
            userService.update(userDTO);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return request.getHeader("referer");
    }
}
