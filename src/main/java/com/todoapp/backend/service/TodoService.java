package com.todoapp.backend.service;

import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.exception.ResourceNotFoundException;
import com.todoapp.backend.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepo todoRepo;

    @Autowired
    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

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
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :" + id));
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepo.save(todo);
    }

    public void delete(Long id) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :" + id));
        todoRepo.delete(todo);
    }
}
