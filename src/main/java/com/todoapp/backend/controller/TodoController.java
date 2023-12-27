package com.todoapp.backend.controller;

import com.todoapp.backend.constants.ApiRouteConstants;
import com.todoapp.backend.dto.ApiResponse;
import com.todoapp.backend.dto.TodoDTO;
import com.todoapp.backend.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiRouteConstants.API_TODOS)
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<Page<TodoDTO>> getAllTodos(Pageable pageable) {
        Page<TodoDTO> todos = todoService.findAll(pageable);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoDTO todoDTO) {
        TodoDTO createdTodoDto = todoService.save(todoDTO);
        return new ResponseEntity<>(createdTodoDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoDTO>> getTodoById(@PathVariable Long id) {
        TodoDTO todoDto = todoService.findById(id);
        ApiResponse<TodoDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Todo fetched successfully", todoDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoDTO todoDetails) {
        TodoDTO updatedTodo = todoService.update(id, todoDetails);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}