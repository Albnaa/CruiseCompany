package com.java.cruisecompany.exceptions;

public class DAOException extends RuntimeException{
    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException() {
        super();
    }
}
