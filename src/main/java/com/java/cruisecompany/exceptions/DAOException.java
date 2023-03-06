package com.java.cruisecompany.exceptions;

/**
 * A custom unchecked exception class for indicating a failure in the DAO layer.
 */
public class DAOException extends RuntimeException {
    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException() {
        super();
    }
}
