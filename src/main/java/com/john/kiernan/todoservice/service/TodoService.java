package com.john.kiernan.todoservice.service;

import com.john.kiernan.todoservice.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> retrieveTodos(String user);
    void saveTodo(Todo todo);
    void removeTodo(long id);
    void updateTodo(Todo todo);
}
