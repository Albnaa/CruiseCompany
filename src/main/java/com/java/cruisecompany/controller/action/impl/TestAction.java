package com.java.cruisecompany.controller.action.impl;

import com.java.cruisecompany.controller.action.Action;
import com.java.cruisecompany.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TestAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String documentPath = "D:\\uploads\\document";
        File document = new File(documentPath, URLDecoder.decode(request.getParameter("documentPath"), StandardCharsets.UTF_8));
        String contentType = request.getServletContext().getMimeType(document.getName());

        System.out.println("Document is present ? " + document.exists());

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(document.length()));
        try {
            Files.copy(document.toPath(), response.getOutputStream());
        } catch (IOException e) {
            System.out.println("Catch block in document action");
            throw new RuntimeException(e);
        }
        return request.getHeader("referer");

    }
}
