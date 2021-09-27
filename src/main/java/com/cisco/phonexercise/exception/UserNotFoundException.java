package com.cisco.phonexercise.exception;

/**
 * The class UserNotFoundException
 *
 * This exception can be thrown when the user not found in db
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs UserNotFoundException object with the specified error message
     * @param message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Contrstruct UserNotFoundException object with specified error message and cause
     * @param message
     * @param cause
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
