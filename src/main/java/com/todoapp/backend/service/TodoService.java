package com.todoapp.backend.service;

import com.todoapp.backend.constants.Constants;
import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.exception.ResourceNotFoundException;
import com.todoapp.backend.repo.TodoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;

    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    public Todo save(Todo todo) {
        return todoRepo.save(todo);
    }

    public Optional<Todo> findById(Long id) {
        return todoRepo.findById(id);
    }

    public Todo update(Long id, Todo todoDetails) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.TODO_NOT_FOUND + id));
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepo.save(todo);
    }

    public void delete(Long id) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.TODO_NOT_FOUND + id));
        todoRepo.delete(todo);
    }
}