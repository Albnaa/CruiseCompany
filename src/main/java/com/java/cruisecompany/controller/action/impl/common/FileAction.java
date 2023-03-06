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

/**
 * This class implements the Action interface and is responsible for handling file viewing or downloading
 * requested by the client.
 */
@Log4j2
public class FileAction implements Action {
    /**
     * Executes the file download action by retrieving the file from the specified directory and streaming it to the
     * client's browser for viewing or downloading.
     *
     * @param request  the HttpServletRequest object containing the file path parameter
     * @param response the HttpServletResponse object used to set the content type, content length, and stream the file
     *                 to the client
     * @return a string indicating that nothing further needs to be done after the file download
     * @throws RuntimeException if an error occurs while streaming the file to the client
     */
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
        return "doNothing";
    }
}
