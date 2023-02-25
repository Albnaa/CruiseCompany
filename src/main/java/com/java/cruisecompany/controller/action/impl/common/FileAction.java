package com.java.cruisecompany.controller.action.impl.common;

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
public class FileAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String directory = "E:\\uploads";
        File file = new File(directory, URLDecoder.decode(request.getParameter("path"), StandardCharsets.UTF_8));
        String contentType = request.getServletContext().getMimeType(file.getName());

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        try {
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (IOException e) {
            log.error("Error in file action -> " + e.getMessage());
            throw new RuntimeException(e);
        }
        return request.getHeader("referer");
    }
}
