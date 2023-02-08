package com.java.cruisecompany.controller.action.impl.admin.ship;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.controller.appcontext.AppContext;
import com.java.cruisecompany.exceptions.ServiceException;
import com.java.cruisecompany.model.service.ShipService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class UpdateShipImageAction implements Action {
    ShipService shipService = AppContext.getInstance().getShipService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String imagePath;
        try {
            imagePath = addImage(request);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        try {
            shipService.updateImage(Long.parseLong(id), imagePath);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return request.getHeader("referer");
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
