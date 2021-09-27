package com.cisco.phonexercise.exception;

/**
 * The class UserPhoneException
 *
 * This class is used as Generic/Parent exception
 */
public class UserPhoneException extends RuntimeException {

    /**
     * creates UserPhoneException object using error message
     * @param message
     */
    public UserPhoneException(String message) {
        super(message);
    }

    /**
     * creates UserPhoneException object using error message and cause
     * @param message
     * @param cause
     */
    public UserPhoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
