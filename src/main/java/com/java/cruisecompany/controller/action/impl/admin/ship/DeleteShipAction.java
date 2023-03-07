package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * Action class that handles deleting a Ship by its ID.
 */
@Log4j2
public class DeleteShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();

    /**
     * Executes the delete ship action by deleting a ship with the given ID.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the JSP for managing ships
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            shipService.delete(Long.parseLong(request.getParameter("id")));
        } catch (ServiceException e) {
            log.error("Error in delete ship action -> " + e.getMessage());
        }
        return "controller?action=manage_ship";
    }
}
