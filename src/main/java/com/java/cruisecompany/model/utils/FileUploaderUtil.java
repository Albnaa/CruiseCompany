package com.java.cruisecompany.model.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

/**
 * A utility class providing static methods for uploading files.
 */
public final class FileUploaderUtil {
    private FileUploaderUtil() {
    }

    /**
     * Uploads an image file to the specified location.
     *
     * @param request the HTTP servlet request containing the image file to upload
     * @return a String representing the absolute path of the uploaded file
     * @throws ServletException if there is an issue with the servlet request
     * @throws IOException      if there is an issue with the I/O operations
     */
    public static String addImage(HttpServletRequest request) throws ServletException, IOException {
        Part filePart = request.getPart("image");
        String filePath = "E:\\uploads\\image\\";
        return addFile(filePart, filePath);
    }

    /**
     * Uploads a document file to the specified location.
     *
     * @param request the HTTP servlet request containing the document file to upload
     * @return a String representing the absolute path of the uploaded file
     * @throws ServletException if there is an issue with the servlet request
     * @throws IOException      if there is an issue with the I/O operations
     */
    public static String addDocument(HttpServletRequest request) throws ServletException, IOException {
        Part filePart = request.getPart("document");
        String filePath = "E:\\uploads\\document\\";
        return addFile(filePart, filePath);
    }

    /**
     * Uploads a file to the specified location.
     *
     * @param filePart the part of the file to upload
     * @param filePath the absolute path of the directory to save the file in
     * @return a String representing the absolute path of the uploaded file
     * @throws IOException if there is an issue with the I/O operations
     */
    private static String addFile(Part filePart, String filePath) throws IOException {
        String fileName = filePart.getSubmittedFileName();

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
