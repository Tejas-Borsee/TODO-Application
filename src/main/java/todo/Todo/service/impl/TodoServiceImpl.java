package todo.Todo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import todo.Security.filter.UserAuthentication;
import todo.Todo.constants.TodoConstants;
import todo.Todo.constants.TodoTagsConstant;
import todo.Todo.domain.request.TodoRequest;
import todo.Todo.domain.request.UpdateTodoRequest;
import todo.Todo.domain.response.TodoResponse;
import todo.Todo.helper.TodoHelper;
import todo.Todo.model.Todo;
import todo.Todo.model.TodoTag;
import todo.Todo.repository.TodoRepository;
import todo.Todo.repository.TodoTagsRepository;
import todo.Todo.service.ITodoService;
import todo.Users.constants.UserConstants;
import todo.Users.model.Users;
import todo.Users.repository.UserRepository;
import todo.common.domain.enums.Status;
import todo.common.domain.response.AppResponse;
import todo.common.exception.NotFoundException;
import todo.common.exception.UnprocessableException;
import todo.common.utils.CommonUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static todo.Todo.constants.TodoConstants.*;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements ITodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final TodoTagsRepository todoTagsRepository;

    @Override
    public AppResponse<TodoResponse> addTodo(TodoRequest todoRequest) {

        boolean isTodoExists = todoRepository.existsByTitle(todoRequest.getTitle());
        if (isTodoExists){
            throw new UnprocessableException(TODO_ALREADY_EXISTS);
        }

        TodoTag todoTag = todoTagsRepository.findById(todoRequest.getTagId())
                .orElseThrow(() -> new NotFoundException(TodoTagsConstant.TAG_NOT_FOUND));

        Users user = userRepository.findByUserId(CommonUtils.getUserAuthentication().getUserId())
                .orElseThrow(() -> new NotFoundException(UserConstants.USER_NOT_FOUND));

        Todo todo = Todo.builder()
                .title(todoRequest.getTitle())
                .priority(todoRequest.getPriority())
                .todoStatus(todoRequest.getTodoStatus())
                .dueDate(LocalDate.parse(todoRequest.getDueDate()))
                .estimatedTime(LocalDate.parse(todoRequest.getEstimatedTime()))
                .user(user)
                .build();
        todo.getTags().add(todoTag);
        Todo savedTodo = todoRepository.save(todo);
        TodoResponse todoResponse = TodoHelper.buildTodoResponse(savedTodo);
        return new AppResponse<>(HttpStatus.OK.value(), TODO_CREATED_SUCCESS, todoResponse, null);
    }

    @Override
    public AppResponse<TodoResponse> getTodo(String id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TODO_NOT_EXISTS));
        TodoResponse todoResponse = TodoHelper.buildTodoResponse(todo);
        return new AppResponse<>(HttpStatus.OK.value(), TODO_FETCHED_SUCCESS, todoResponse, null);
    }

    @Override
    public AppResponse<List<TodoResponse>> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        List<TodoResponse> todoResponses = todos.stream()
                .map(TodoHelper::buildTodoResponse)
                .toList();

        return new AppResponse<>(HttpStatus.OK.value(), TODO_FETCHED_SUCCESS, todoResponses, null);
    }

    @Override
    public AppResponse<TodoResponse> updateTodo(String id, UpdateTodoRequest request) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TODO_NOT_EXISTS));

        TodoTag todoTag = todoTagsRepository.findByTag(request.getTag())
                        .orElseThrow(() -> new NotFoundException(TodoTagsConstant.TAG_NOT_FOUND));

        todo.setTitle(request.getTitle());
        todo.setPriority(request.getPriority());
        todo.setTodoStatus(request.getTodoStatus());
        todo.setDueDate(request.getDueDate());
        todo.setEstimatedTime(request.getEstimatedTime());
        todo.setActualTimeTaken(request.getActualTimeTaken());
        todo.getTags().add(todoTag);
        Todo updatedTodo = todoRepository.save(todo);
        TodoResponse todoResponse = TodoHelper.buildTodoResponse(updatedTodo);
        return new AppResponse<>(HttpStatus.OK.value(), TODO_UPDATED_SUCCESS, todoResponse, null);
    }

    @Override
    public AppResponse<String> deleteTodo(String id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TODO_NOT_EXISTS));

        todo.setStatus(Status.DELETED);
        todoRepository.save(todo);
        return new AppResponse<>(HttpStatus.OK.value(), TODO_DELETED_SUCCESS, null, null);
    }

    @Override
    public AppResponse<TodoResponse> getTodoByTag(String tag) {
        return new AppResponse<>(HttpStatus.OK.value(), TODO_FETCHED_SUCCESS, null, null);
    }
}
