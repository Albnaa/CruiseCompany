package com.java.cruisecompany.model.utils;

/**
 * The {@code ExceptionUtil} class provides utility methods for handling exceptions.
 */
public final class ExceptionUtil {
    /**
     * Private constructor to prevent instantiation of the class.
     */
    private ExceptionUtil() {
    }

    /**
     * Returns the root message of a {@link Throwable} object by recursively traversing its causes.
     *
     * @param e the {@link Throwable} object whose root message is to be obtained.
     * @return the root message of the {@link Throwable} object, or null if the object has no message.
     */
    public static String getRootMessage(Throwable e) {
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return e.getMessage();
    }

    /**
     * Formats an error message by replacing a placeholder in the message with a specified action.
     *
     * @param message the error message to be formatted.
     * @param action  the action to be inserted into the error message.
     * @return the formatted error message, with the action inserted into the message.
     */
    public static String remapMessage(String message, String action) {
        String[] parts = message.split("\\.");
        return String.format("error.%s.%s", action, parts[parts.length - 2]);
    }
}
