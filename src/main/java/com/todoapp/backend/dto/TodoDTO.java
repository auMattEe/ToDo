package com.todoapp.backend.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
@Builder
public class TodoDTO {
    private Long id;

    @NotBlank(message="Title is required")
    private String title;

    private String description;
    private boolean completed;
}