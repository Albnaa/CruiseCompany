package com.java.cruisecompany.controller.action.impl;

import com.java.cruisecompany.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Log4j2
public class TestAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String documentPath = "E:\\uploads\\document";
        File document = new File(documentPath, URLDecoder.decode(request.getParameter("documentPath"), StandardCharsets.UTF_8));
        String contentType = request.getServletContext().getMimeType(document.getName());

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(document.length()));
        try {
            Files.copy(document.toPath(), response.getOutputStream());
        } catch (IOException e) {
            log.error("Error in test action -> " + e.getMessage());
            throw new RuntimeException(e);
        }
        return request.getHeader("referer");

    }
}
