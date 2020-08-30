package com.john.kiernan.todoservice.dao;

import com.john.kiernan.todoservice.model.Todo;

import java.util.List;

public interface TodoDao {
    List<Todo> getUserTodos(String username);
    void saveTodo(Todo todo);
    void removeTodo(long id);
    void updateTodo(Todo todo);
}
