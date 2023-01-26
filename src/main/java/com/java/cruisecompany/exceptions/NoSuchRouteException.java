package com.java.cruisecompany.exceptions;

public class NoSuchRouteException extends ServiceException{

    public NoSuchRouteException() {
        super("error.doNotExistRoute");
    }
}
