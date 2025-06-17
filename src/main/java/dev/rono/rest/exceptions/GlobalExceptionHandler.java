package dev.rono.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // This class will handle global exceptions for the REST API
    // It uses Spring's @RestControllerAdvice annotation to indicate that it is a global exception handler

    // Handle ResourceNotFoundException globally (specified by @ExceptionHandler)
    // This method will be called whenever a ResourceNotFoundException is thrown in the application
    // It will return a 404 Not Found response with an error message
    // The @ResponseStatus annotation sets the HTTP status code for the response
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Handle ResourceNotFoundException and return a 404 Not Found response
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

}
