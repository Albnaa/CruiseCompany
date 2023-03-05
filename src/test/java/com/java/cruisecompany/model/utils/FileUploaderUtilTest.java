package com.java.cruisecompany.model.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileUploaderUtilTest {
    @Mock
    HttpServletRequest mockRequest;

    @Mock
    Part mockPart;

    @TempDir
    File tempDir;

    File testDir;
    String testFilePath;

    @BeforeEach
    void setup() {
        testDir = new File(tempDir, "testDir");
        testDir.mkdir();
        testFilePath = new File(testDir, "testFile.txt").getAbsolutePath();
        when(mockPart.getSubmittedFileName()).thenReturn("testFile.txt");
    }

    @Test
    void addImage_shouldReturnCorrectPath() throws ServletException, IOException {
        when(mockRequest.getPart("image")).thenReturn(mockPart);
        String expected = "/image/testFile.txt";
        String actual = FileUploaderUtil.addImage(mockRequest);
        assertEquals(expected, actual);
    }
    @Test
    void addDocument_shouldReturnCorrectPath() throws ServletException, IOException {
        when(mockRequest.getPart("document")).thenReturn(mockPart);
        String expected = "/document/testFile.txt";
        String actual = FileUploaderUtil.addDocument(mockRequest);
        assertEquals(expected, actual);
    }
}