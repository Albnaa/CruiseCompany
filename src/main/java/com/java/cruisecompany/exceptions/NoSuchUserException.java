package com.java.cruisecompany.exceptions;

public class NoSuchUserException extends Exception {
    public NoSuchUserException() {
        super("error.doNotExist.user");
    }
}
