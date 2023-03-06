package com.java.cruisecompany.exceptions;

/**
 * Exception to be thrown when a requested route does not exist in the database.
 */
public class NoSuchRouteException extends ServiceException {
    public NoSuchRouteException() {
        super("error.doNotExistRoute");
    }
}
