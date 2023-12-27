package com.todoapp.backend.constants;

public final class ErrorMessages {
    public static final String TODO_NOT_FOUND = "Todo not found with id ";
    public static final String GENERIC_ERROR = "An error occurred while executing request.";
    // Add more error messages here as constants

    private ErrorMessages() {
        throw new IllegalStateException("Constants class");
    }
}