package com.todoapp.backend.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomErrorResponse {
    private int errorCode;
    private String errorMessage;

}