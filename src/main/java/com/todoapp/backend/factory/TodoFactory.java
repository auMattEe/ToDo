package com.todoapp.backend.factory;

import com.todoapp.backend.dto.TodoDTO;
import com.todoapp.backend.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoFactory {

    public Todo convertToEntity(TodoDTO dto) {
        return Todo.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .build();
    }

    public TodoDTO convertToDTO(Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .build();
    }
}