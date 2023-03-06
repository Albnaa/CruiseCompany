package com.java.cruisecompany.exceptions;

/**
 * Exception to be thrown when a requested port does not exist in the database.
 */
public class NoSuchPortException extends ServiceException {
    public NoSuchPortException() {
        super("error.doNotExist.port");
    }
}
