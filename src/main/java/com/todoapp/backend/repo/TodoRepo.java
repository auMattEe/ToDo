package com.todoapp.backend.repo;

import com.todoapp.backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Long> {}
