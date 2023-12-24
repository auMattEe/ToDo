package com.todoapp.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        //CustomErrorResponse is a POJO you need to create with whatever fields you want in error response
        CustomErrorResponse response = new CustomErrorResponse();
        response.setErrorCode(HttpStatus.NOT_FOUND.value());
        response.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //You can define other @ExceptionHandler methods for other types of exceptions

}