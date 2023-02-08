package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.validation.ShipValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String capacity = request.getParameter("capacity");
        String visitedPorts = request.getParameter("visitedPorts");
        String staff = request.getParameter("staff");
        String imagePath = "";

        Map<String, String> errors = validateShipParameters(name, capacity, visitedPorts, staff);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        try {
            imagePath = addImage(request);
        } catch (IOException | ServletException e) {
            System.out.println("Exception file");
            System.out.println(e.getMessage());
        }

        ShipDTO ship = ShipDTO.builder()
                .name(name)
                .capacity(Integer.parseInt(capacity))
                .visitedPorts(Integer.parseInt(visitedPorts))
                .staff(Integer.parseInt(staff))
                .imagePath(imagePath)
                .build();

        try {
            shipService.create(ship);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return "controller?action=manage_ship";
    }

    private Map<String, String> validateShipParameters(String name, String capacity, String visitedPorts, String staff) {
        Map<String, String> errors = new HashMap<>();
        ShipValidator.validateShipName(name, "create.ship", errors);
        ShipValidator.validateShipCapacity(capacity, "create.ship", errors);
        ShipValidator.validateShipVisitedPorts(visitedPorts, "create.ship", errors);
        ShipValidator.validateShipStaff(staff, "create.ship", errors);
        return errors;
    }

    private String addImage(HttpServletRequest request) throws ServletException, IOException {
        Part filePart = request.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        String filePath = "D:\\uploads\\image\\";

        File file = new File(filePath + fileName);
        int i = 1;
        while (file.exists()) {
            file = new File(filePath + fileName.substring(0, fileName.lastIndexOf("."))
                    + "_" + i + fileName.substring(fileName.lastIndexOf(".")));
            i++;
        }

        filePart.write(file.getAbsolutePath());

        return file.getAbsolutePath().split("uploads")[1].replace("\\", "/");
    }
}
