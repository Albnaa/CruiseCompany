package com.java.cruisecompany.exceptions;

/**
 * Exception to be thrown when a requested ship does not exist in the database.
 */
public class NoSuchShipException extends ServiceException {
    public NoSuchShipException() {
        super("error.doNotExistShip");
    }
}
