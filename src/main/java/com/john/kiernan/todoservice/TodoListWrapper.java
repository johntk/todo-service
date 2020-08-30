package com.john.kiernan.todoservice;

import com.john.kiernan.todoservice.model.Todo;

import java.util.ArrayList;

public class TodoListWrapper {
    private ArrayList<Todo> todo;

    public TodoListWrapper() {
        this.todo = new ArrayList<Todo>();
    }

    public ArrayList<Todo> getTodos() {
        return todo;
    }

    public void setTodos(ArrayList<Todo> users) {
        this.todo = users;
    }

    public void add(Todo todo) {
        this.todo.add(todo);
    }
}
