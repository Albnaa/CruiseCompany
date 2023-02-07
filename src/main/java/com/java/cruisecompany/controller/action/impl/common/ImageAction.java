package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ImageAction implements Action {
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String imagePath = "C:\\Users\\olego\\IdeaProjects\\CruiseCompany\\src\\main\\webapp";
        File image = new File(imagePath, URLDecoder.decode(request.getPathInfo(), StandardCharsets.UTF_8));
        String contentType = request.getServletContext().getMimeType(image.getName());

        return request.getHeader("referer");

    }
}
