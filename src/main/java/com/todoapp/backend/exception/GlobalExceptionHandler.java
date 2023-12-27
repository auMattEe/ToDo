package com.todoapp.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.todoapp.backend.constants.ErrorMessages;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        CustomErrorResponse response = new CustomErrorResponse(ex, request.getRequestURI(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Catch-all handler
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRemainingExceptions(RuntimeException ex, HttpServletRequest request) {
        // Log actual exception but only return a generic message
        CustomErrorResponse response = new CustomErrorResponse(ErrorMessages.GENERIC_ERROR, request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}