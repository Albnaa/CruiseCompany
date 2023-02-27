package com.java.cruisecompany.model.utils;

public final class ExceptionUtil {
    private ExceptionUtil() {}

    public static String getRootMessage(Throwable e) {
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return e.getMessage();
    }

    public static String remapMessage(String message, String action) {
        String[] parts = message.split("\\.");
        return String.format("error.%s.%s", action, parts[parts.length - 2]);
    }
}
