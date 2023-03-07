package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.FileUploaderUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

/**
 * Action class that handles the updating of a ship image.
 */
@Log4j2
public class UpdateShipImageAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();

    /**
     * Executes the ship update image action by updating the image of a ship with a given id.
     *
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return the URL of the previous page, obtained from the request header
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String imagePath;

        try {
            imagePath = FileUploaderUtil.addImage(request);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }

        try {
            shipService.updateImage(Long.parseLong(id), imagePath);
        } catch (ServiceException e) {
            log.error("Error in ship update image action -> " + e.getMessage());
        }
        return request.getHeader("referer");
    }
}
