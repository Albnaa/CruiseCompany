package com.java.cruisecompany.exceptions;

/**
 * Exception to be thrown when a requested user does not exist in the database.
 */
public class NoSuchUserException extends ServiceException {
    public NoSuchUserException() {
        super("error.doNotExist.user");
    }
}
