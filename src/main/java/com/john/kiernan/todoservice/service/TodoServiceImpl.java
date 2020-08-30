package com.john.kiernan.todoservice.service;

import com.john.kiernan.todoservice.dao.TodoDao;
import com.john.kiernan.todoservice.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    public List<Todo> retrieveTodos(String user) {
        return todoDao.getUserTodos(user).stream()
                .filter(todo -> todo.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public void saveTodo(Todo todo) {
        todoDao.saveTodo(todo);
    }

    @Override
    public void removeTodo(long id) {
        todoDao.removeTodo(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }
}
