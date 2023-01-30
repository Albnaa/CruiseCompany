package com.java.cruisecompany.exceptions;

public class NoSuchTicketException extends ServiceException{
    public NoSuchTicketException() {
        super("error.doNotExist.ticket");
    }
}
