package todo.Todo.service;

import todo.Todo.domain.request.TodoRequest;
import todo.Todo.domain.request.UpdateTodoRequest;
import todo.Todo.domain.response.TodoResponse;
import todo.common.domain.response.AppResponse;

import java.util.List;

public interface ITodoService {
    
    AppResponse<TodoResponse> addTodo(TodoRequest todoRequest);

    AppResponse<TodoResponse> getTodo(String id);
    
    AppResponse<List<TodoResponse>> getAllTodos();

    AppResponse<TodoResponse> updateTodo(String id, UpdateTodoRequest updateTodoRequest);

    AppResponse<String> deleteTodo(String id);
}
