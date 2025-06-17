package dev.rono.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException {
    // This class represents a custom exception for resource not found scenarios
    // It can be extended from RuntimeException or Exception based on your needs
    public ResourceNotFoundException(String message) {
        super(message); // Call the constructor of the superclass with the error message
    }
}
