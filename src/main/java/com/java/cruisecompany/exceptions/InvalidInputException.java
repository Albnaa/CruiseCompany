package com.java.cruisecompany.exceptions;

/**
 * An exception that is thrown when the input provided by the user is invalid.
 */
public class InvalidInputException extends ServiceException {
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
