package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.dto.ShipDTO;
import com.java.cruisecompany.model.service.ShipService;
import com.java.cruisecompany.model.utils.validation.ShipValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class UpdateShipAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String capacity = request.getParameter("capacity");
        String visitedPorts = request.getParameter("visitedPorts");
        String staff = request.getParameter("staff");

        Map<String, String> errors = validateRouteParameters(id, name, capacity, visitedPorts, staff);

        if (!errors.isEmpty()) {
            request.getSession().setAttribute("errors", errors);
            return request.getHeader("referer");
        }
        request.getSession().removeAttribute("errors");

        ShipDTO ship = ShipDTO.builder()
                .id(Long.parseLong(id))
                .name(name)
                .capacity(Integer.parseInt(capacity))
                .visitedPorts(Integer.parseInt(visitedPorts))
                .staff(Integer.parseInt(staff))
                .build();

        try {
            shipService.update(ship);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return request.getHeader("referer");
    }

    private Map<String, String> validateRouteParameters(String id, String name, String capacity, String visitedPorts, String staff) {
        Map<String, String> errors = new HashMap<>();
        ShipValidator.validateShipId(id, "update.ship", errors);
        ShipValidator.validateShipName(name, "update.ship", errors);
        ShipValidator.validateShipCapacity(capacity, "update.ship", errors);
        ShipValidator.validateShipVisitedPorts(visitedPorts, "update.ship", errors);
        ShipValidator.validateShipStaff(staff, "update.ship", errors);
        return errors;
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if (action != null && action.equals("view-image")) {
//            // Get the path to the image from the query string
//            String imagePath = request.getParameter("path");
//
//            // Read the image file from the file system
//            File imageFile = new File("/path/to/images" + imagePath);
//            try (InputStream fileStream = new FileInputStream(imageFile)) {
//                // Set the content type of the response to match the image file format
//                response.setContentType(getServletContext().getMimeType(imageFile.getName()));
//                response.setContentLength((int) imageFile.length());
//
//                // Write the image data to the response
//                OutputStream outputStream = response.getOutputStream();
//                byte[] buffer = new byte[4096];
//                int bytesRead;
//                while ((bytesRead = fileStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//            }
//        } else {
//            UpdateShipAction updateShipAction = new UpdateShipAction();
//            String result = updateShipAction.execute(request);
//            request.getRequestDispatcher(result).forward(request, response);
//        }
//    }
}
