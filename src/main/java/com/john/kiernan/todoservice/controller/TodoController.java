package com.john.kiernan.todoservice.controller;

import com.john.kiernan.todoservice.TodoListWrapper;
import com.john.kiernan.todoservice.model.Todo;
import com.john.kiernan.todoservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


@Controller
public class TodoController {

    @Autowired
    TodoService service;

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {

        TodoListWrapper todoListWrapper = new TodoListWrapper();
        todoListWrapper.setTodos((ArrayList<Todo>) service.retrieveTodos(getUser()));

        model.put("todoListWrapper", todoListWrapper);
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String addTodo() {
        return "add-todo";
    }

    @RequestMapping(value = "/save-todo", method =  RequestMethod.POST)
    public String saveTodo(Todo todo) {
        todo.setUser(getUser());
        todo.setLastUpdated(Date.valueOf(LocalDate.now()));
        service.saveTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/remove-todo", method = RequestMethod.GET)
    public String removeTodo(@RequestParam long id){
        service.removeTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(@ModelAttribute("todoListWrapper") TodoListWrapper todoListWrapper){

        for(Todo todo : todoListWrapper.getTodos()){
            todo.setUser(getUser());
            todo.setLastUpdated(Date.valueOf(LocalDate.now()));
            service.updateTodo(todo);
        }

        return "redirect:/list-todos";
    }

    private String getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return principal instanceof UserDetails ?
                ((UserDetails) principal).getUsername() :
                principal.toString();
    }

}
