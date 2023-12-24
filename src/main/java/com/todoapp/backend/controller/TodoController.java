package com.todoapp.backend.controller;

import com.todoapp.backend.dto.TodoDTO;
import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public TodoDTO createTodo(@Valid @RequestBody TodoDTO todoDTO) {
        Todo todo = convertToEntity(todoDTO);
        Todo savedTodo = todoService.save(todo);
        return convertToDTO(savedTodo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.findById(id);
        return ResponseEntity.ok().body(convertToDTO(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoDTO todoDetails) {
        Todo todo = convertToEntity(todoDetails);
        todo.setId(id);
        Todo updatedTodo = todoService.update(todo);
        return ResponseEntity.ok().body(convertToDTO(updatedTodo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private TodoDTO convertToDTO(Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setCompleted(todo.isCompleted());
        return dto;
    }

    private Todo convertToEntity(TodoDTO dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.isCompleted());
        return todo;
    }
}