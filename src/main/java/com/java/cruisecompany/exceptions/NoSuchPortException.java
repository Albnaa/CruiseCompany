package com.java.cruisecompany.exceptions;

public class NoSuchPortException extends ServiceException{
    public NoSuchPortException() {
        super("error.doNotExist.port");
    }
}
