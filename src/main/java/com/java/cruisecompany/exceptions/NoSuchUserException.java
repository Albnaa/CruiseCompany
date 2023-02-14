package com.java.cruisecompany.exceptions;

public class NoSuchUserException extends ServiceException {
    public NoSuchUserException() {
        super("error.doNotExist.user");
    }
}
