package todo.Todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.Todo.domain.request.TodoRequest;
import todo.Todo.domain.request.UpdateTodoRequest;
import todo.Todo.domain.response.TodoResponse;
import todo.Todo.service.ITodoService;
import todo.common.constants.ApiConstants;
import todo.common.domain.response.AppResponse;

import java.util.List;

@RestController
@RequestMapping(value = ApiConstants.BASE_URI + ApiConstants.TODO_URI)
public class TodoController {

    private final ITodoService todoService;

    public TodoController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/add-todo")
    public ResponseEntity<AppResponse<TodoResponse>> addTodo(@RequestBody TodoRequest todoRequest){
        return ResponseEntity.ok(todoService.addTodo(todoRequest));
    }

    @GetMapping("/get-todo")
    public ResponseEntity<AppResponse<TodoResponse>> getTodo(@RequestParam String id){
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    @GetMapping("/get-all-todos")
    public ResponseEntity<AppResponse<List<TodoResponse>>> getAllTodos(){
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PutMapping("/update-todo")
    public ResponseEntity<AppResponse<TodoResponse>> updateTodo(@RequestParam String id, @RequestBody UpdateTodoRequest updateTodoRequest){
        return ResponseEntity.ok(todoService.updateTodo(id, updateTodoRequest));
    }

    @DeleteMapping("/delete-todo")
    public ResponseEntity<AppResponse<String>> deleteTodo(@RequestParam String id){
        return ResponseEntity.ok(todoService.deleteTodo(id));
    }

}
