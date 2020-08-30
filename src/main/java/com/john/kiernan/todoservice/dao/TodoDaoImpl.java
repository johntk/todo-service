package com.john.kiernan.todoservice.dao;

import com.john.kiernan.todoservice.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoDaoImpl implements TodoDao {

   private final String FIND_TODOS_BY_USER = "SELECT * FROM TODOS WHERE USER=:USER";
   private final String INSERT_TODO_BY_ID = "INSERT INTO TODOS (ID, USER, DESC, LASTUPDATED) VALUES (TODO_SEQUENCE.NEXTVAL, :USER, :DESC, :LASTUPDATED)";
   private final String DELETE_TODO_BY_ID = "DELETE FROM TODOS WHERE ID = :ID";
   private final String UPDATE_TODO_BY_ID = "UPDATE TODOS SET DESC = :DESC, LASTUPDATED =:LASTUPDATED, DONE = :DONE WHERE ID = :ID";


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Todo> getUserTodos(String user) {
        Map<String, Object> params = new HashMap<>();
        params.put("USER", user);

        return namedParameterJdbcTemplate.query(
                FIND_TODOS_BY_USER, params,
                (rs, rowNum) ->
                        new Todo(
                                rs.getInt("id"),
                                rs.getString("user"),
                                rs.getString("desc"),
                                rs.getDate("lastUpdated"),
                                Boolean.parseBoolean(rs.getString("done")))
        );
    }

    @Override
    public void saveTodo(Todo todo) {
        Map<String, Object> params = new HashMap<>();
        params.put("USER", todo.getUser());
        params.put("DESC", todo.getDesc());
        params.put("LASTUPDATED", todo.getLastUpdated());
        params.put("DONE", todo.isDone());

        namedParameterJdbcTemplate.update(INSERT_TODO_BY_ID, params);
    }

    @Override
    public void removeTodo(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);
        namedParameterJdbcTemplate.update(DELETE_TODO_BY_ID, params);
    }

    @Override
    public void updateTodo(Todo todo) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", todo.getId());
        params.put("USER", todo.getUser());
        params.put("DESC", todo.getDesc());
        params.put("LASTUPDATED", todo.getLastUpdated());
        params.put("DONE", todo.isDone());
        namedParameterJdbcTemplate.update(UPDATE_TODO_BY_ID, params);
    }
}
