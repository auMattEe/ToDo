package com.todoapp.backend.exception;

import jakarta.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Resource not found with id: ";

    public ResourceNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }

    public ResourceNotFoundException(String message) {
        super(DEFAULT_MESSAGE + message);
    }
}