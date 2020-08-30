package com.john.kiernan.todoservice;

import com.john.kiernan.todoservice.model.Todo;
import com.john.kiernan.todoservice.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(classes = TodoServiceApplication.class)
@WebAppConfiguration
@Transactional
class TodoServiceTests {


	@Autowired
	private TodoService todoService;

	@Test
	public void testRetrieveTodos() {
		assertThat(todoService.retrieveTodos("user1"), hasSize(1));
	}

	@Test
	public void testSaveTodo() {
		todoService.saveTodo(getTodo("john", "test1"));
		todoService.saveTodo(getTodo("john", "test2"));

		assertThat(todoService.retrieveTodos("john"), hasSize(2));
	}

	@Test
	public void testRemoveTodo() {
		todoService.saveTodo(getTodo("john", "test1"));
		todoService.saveTodo(getTodo("john", "test2"));

		Long id = getTodoId("john", "test1");

		todoService.removeTodo(id);
		assertThat(todoService.retrieveTodos("john"), hasSize(1));
	}

	@Test
	public void testUpdateTodo() {
		todoService.saveTodo(getTodo("john", "test1"));

		Long id =  getTodoId("john", "test1");
		Todo todo = getTodo("john", "test2");
		todo.setId(id);

		todoService.updateTodo(todo);

		List<Todo> todoList = todoService.retrieveTodos("john");

		String desc = todoList.stream().
				filter(t -> t.getDesc().equals("test2"))
				.map(Todo::getDesc)
				.findFirst()
				.orElse("fail");

		assertThat(todoList, hasSize(1));
		assertThat(desc, equalToIgnoringCase("test2"));



	}

	private Long getTodoId(String user, String desc) {

		List<Todo> todoList = todoService.retrieveTodos(user);

		return todoList.stream().
				filter(todo -> todo.getDesc().equals(desc))
				.map(Todo::getId)
				.findFirst()
				.orElse(99L);
	}

	private Todo getTodo(String user, String desc) {
		Todo todo = new Todo();
		todo.setUser(user);
		todo.setDesc(desc);
		todo.setDone(false);

		return todo;
	}

}
