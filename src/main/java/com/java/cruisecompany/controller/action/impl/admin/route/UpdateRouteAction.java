package com.java.cruisecompany.controller.action.impl.admin.route;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.RouteDTO;
import com.java.cruisecompany.model.service.RouteService;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

public class UpdateRouteAction implements Action {
    RouteService routeService = AppContext.getInstance().getRouteService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        RouteDTO routeDTO = RouteDTO.builder()
                .id(Long.parseLong(request.getParameter("routeId")))
                .name(request.getParameter("routeName"))
                .startOfCruise(LocalDate.parse(request.getParameter("routeStart")))
                .endOfCruise(LocalDate.parse(request.getParameter("routeEnd")))
                .build();
        try {
            routeService.update(routeDTO);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return request.getHeader("referer");
    }
}
