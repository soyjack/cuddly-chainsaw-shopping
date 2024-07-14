package com.hamy.tradeshop.exception;

/**
 * The ResourceNotFoundException is a custom exception that is thrown when a requested resource is not found.
 * It extends RuntimeException, allowing it to be used as an unchecked exception.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
