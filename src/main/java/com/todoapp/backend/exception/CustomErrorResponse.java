package com.todoapp.backend.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class CustomErrorResponse {

    private HttpStatus status;
    private String error;
    private List<String> messages;
    private String path;
    private LocalDateTime timestamp;

    public CustomErrorResponse(Exception ex, String path, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status ;
        this.error = status.getReasonPhrase();
        this.messages = List.of(ex.getLocalizedMessage());
        this.path = path;
    }

    public CustomErrorResponse(List<String> messages, String path, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = status.getReasonPhrase();
        this.messages = messages;
        this.path = path;
    }

    public CustomErrorResponse(String message, String path, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = status.getReasonPhrase();
        this.messages = Collections.singletonList(message);
        this.path = path;
    }
}