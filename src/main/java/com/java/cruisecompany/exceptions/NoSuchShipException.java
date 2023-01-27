package com.java.cruisecompany.exceptions;

public class NoSuchShipException extends ServiceException{
    public NoSuchShipException() {
        super("error.doNotExistShip");
    }
}
