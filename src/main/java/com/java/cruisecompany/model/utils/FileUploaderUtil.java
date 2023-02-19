package com.java.cruisecompany.model.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public final class FileUploaderUtil {
    private FileUploaderUtil() {}

    public static String addImage(HttpServletRequest request) throws ServletException, IOException {
        Part filePart = request.getPart("image");
        String filePath = "E:\\uploads\\image\\";
        return addFile(filePart, filePath);
    }

    public static String addDocument(HttpServletRequest request) throws ServletException, IOException {
        Part filePart = request.getPart("document");
        String filePath = "E:\\uploads\\document\\";
        return addFile(filePart, filePath);
    }

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
