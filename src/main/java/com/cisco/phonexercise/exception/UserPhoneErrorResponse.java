package com.cisco.phonexercise.exception;

import org.springframework.http.HttpStatus;

/**
 * The class UserPhoneErrorResponse
 *
 * This class is used to send error response to user
 */
public class UserPhoneErrorResponse {

    private String errorMessage;
    private HttpStatus httpStatus;

    /**
     * Creates UserPhoneErrorResponse object using errorMessage, httpStatus and errors
     * @param errorMessage
     * @param httpStatus
     */
    public UserPhoneErrorResponse(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    /**
     * The method getErrorMessage
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * The method setErrorMessage
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * The method getHttpStatus
     * @return
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * The method setHttpStatus
     * @param httpStatus
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
