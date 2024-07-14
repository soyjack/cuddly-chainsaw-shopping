package com.hamy.tradeshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The GlobalExceptionHandler class handles exceptions globally across the application.
 * It provides specific methods to handle different types of exceptions and send appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException exceptions.
     * 
     * @return a ResponseEntity with a 404 NOT FOUND status.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException() {
        // Handle resource not found exceptions
    }

    /**
     * Handles generic Exception exceptions.
     * 
     * @return a ResponseEntity with a 500 INTERNAL SERVER ERROR status.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException() {
        // Handle generic exceptions
    }
}
