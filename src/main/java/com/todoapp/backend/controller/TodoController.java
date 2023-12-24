package com.todoapp.backend.controller;

import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.repo.TodoRepo;
import com.todoapp.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepo todoRepo;

    @Autowired
    public TodoController(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepo.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepo.save(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :" + id));
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        Todo todo = todoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :" + id));
        todo.setName(todoDetails.getName()); //Changed setTitle to setName
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepo.save(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        Todo existingTodo = todoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo id: " + id));
        todoRepo.delete(existingTodo);
        return ResponseEntity.noContent().build();
    }
}