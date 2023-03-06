package com.java.cruisecompany.exceptions;

/**
 * Exception to be thrown when a requested ticket does not exist in the database.
 */
public class NoSuchTicketException extends ServiceException {
    public NoSuchTicketException() {
        super("error.doNotExist.ticket");
    }
}
