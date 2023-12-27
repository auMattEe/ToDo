package com.todoapp.backend.service;

import com.todoapp.backend.constants.ErrorMessages;
import com.todoapp.backend.dto.TodoDTO;
import com.todoapp.backend.entity.Todo;
import com.todoapp.backend.exception.ResourceNotFoundException;
import com.todoapp.backend.factory.TodoFactory;
import com.todoapp.backend.repo.TodoRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Service
@Transactional
public class TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
    private final TodoRepo todoRepo;
    private final TodoFactory todoFactory;

    @Autowired
    public TodoService(TodoRepo todoRepo, TodoFactory todoFactory) {
        this.todoRepo = todoRepo;
        this.todoFactory = todoFactory;
    }

    public Page<TodoDTO> findAll(Pageable pageable) {
        Page<Todo> todos = todoRepo.findAll(pageable);
        return todos.map(todoFactory::convertToDTO);
    }

    public TodoDTO save(TodoDTO todoDTO) {
        logger.info("Attempting to save TODO: {}", todoDTO);
        Todo todo = todoFactory.convertToEntity(todoDTO);
        Todo savedTodo = todoRepo.save(todo);
        logger.info("Successfully saved TODO: {}", savedTodo);
        return todoFactory.convertToDTO(savedTodo);
    }

    public TodoDTO findById(Long id) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.TODO_NOT_FOUND + id));
        return todoFactory.convertToDTO(todo);
    }

    public TodoDTO update(Long id, TodoDTO todoDTO) {
        Todo existingTodo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        existingTodo.setTitle(todoDTO.getTitle());
        existingTodo.setDescription(todoDTO.getDescription());
        existingTodo.setCompleted(todoDTO.isCompleted());
        Todo updatedTodo = todoRepo.save(existingTodo);
        return todoFactory.convertToDTO(updatedTodo);
    }

    public void delete(Long id) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        log.info("Deleting todo with id {}", id);  //Adding log

        todoRepo.delete(todo);
    }
}