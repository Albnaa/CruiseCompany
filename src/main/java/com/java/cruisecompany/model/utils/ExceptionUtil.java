package com.java.cruisecompany.model.utils;

public final class ExceptionUtil {
    private ExceptionUtil() {}

    public static String getRootMessage(Throwable e) {
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return e.getMessage();
    }
}
