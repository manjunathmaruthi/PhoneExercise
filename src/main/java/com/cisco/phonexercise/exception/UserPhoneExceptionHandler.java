package com.cisco.phonexercise.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * The class UserPhoneExceptionHandler
 *
 * This is responsible to handle UserPhoneException, UserNotFoundException
 */
@ControllerAdvice
public class UserPhoneExceptionHandler {

    Logger logger = LoggerFactory.getLogger(UserPhoneExceptionHandler.class);

    /**
     * The method handleUserPhoneException
     * It handles UserPhoneException, which is thrown as generic exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {UserPhoneException.class})
    public ResponseEntity<Object> handleUserPhoneException(UserPhoneException e) {
        logger.error(e.getMessage());
        UserPhoneErrorResponse errorResponse = new UserPhoneErrorResponse(e.getMessage(),
                BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    /**
     * The method handleUserNotFoundException
     * It handles the UserNotFoundException, which will be thrown when user not found in db
     * @param e
     * @return
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        logger.error(e.getMessage());
        UserPhoneErrorResponse errorResponse = new UserPhoneErrorResponse(e.getMessage(),
                BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    /**
     * The method handleConflictException
     * @param e
     * @return
     */
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleConflictException(DataIntegrityViolationException e){
        logger.error(e.getMessage());
        UserPhoneErrorResponse errorResponse = new UserPhoneErrorResponse("Email/PrefferedPhone is duplicate",
                CONFLICT);
        return new ResponseEntity<>(errorResponse, CONFLICT);
    }
}
