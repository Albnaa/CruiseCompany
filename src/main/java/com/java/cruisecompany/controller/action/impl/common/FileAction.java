package com.java.cruisecompany.controller.action.impl.common;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String directory = "D:\\uploads";
        File file = new File(directory, URLDecoder.decode(request.getParameter("path"), StandardCharsets.UTF_8));
        String contentType = request.getServletContext().getMimeType(file.getName());

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        try {
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (IOException e) {
            System.out.println("Catch block in file action");
            throw new RuntimeException(e);
        }
        return request.getHeader("referer");

    }
}
