package todo.Todo.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import todo.Todo.constants.TodoTagsConstant;
import todo.Todo.constants.TodoTagsConstant.*;
import todo.Todo.domain.request.TodoTagsRequest;
import todo.Todo.domain.response.TodoTagsResponse;
import todo.Todo.model.Todo;
import todo.Todo.model.TodoTag;
import todo.Todo.repository.TodoTagsRepository;
import todo.Todo.service.ITodoTagsService;
import todo.common.domain.enums.Status;
import todo.common.domain.response.AppResponse;
import todo.common.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

import static todo.Todo.constants.TodoTagsConstant.*;

@Service
public class TodoTagsServiceImpl implements ITodoTagsService {

    private final TodoTagsRepository todoTagsRepository;

    public TodoTagsServiceImpl(TodoTagsRepository todoTagsRepository){
        this.todoTagsRepository = todoTagsRepository;
    }

    @Override
    public AppResponse<TodoTagsResponse> createTag(TodoTagsRequest todoTagsRequest) {

        TodoTag isTagExists = todoTagsRepository.findByTag(todoTagsRequest.getTag())
                .orElseThrow(() -> new NotFoundException(TAG_NOT_FOUND));

        TodoTag todoTag = TodoTag.builder()
                .tag(todoTagsRequest.getTag())
                .build();
        TodoTag savedTag = todoTagsRepository.save(todoTag);

        TodoTagsResponse todoTagsResponse = TodoTagsResponse.builder()
                .id(savedTag.getId())
                .tag(savedTag.getTag())
                .build();

        return new AppResponse<>(HttpStatus.OK.value(), TAG_CREATED_SUCCESS, todoTagsResponse, null);
    }

    @Override
    public AppResponse<TodoTagsResponse> getTag(String tag) {

        TodoTag todoTag = todoTagsRepository.findByTag(tag)
                .orElseThrow(() -> new NotFoundException(TAG_NOT_FOUND));

        TodoTagsResponse response = TodoTagsResponse.builder()
                .id(todoTag.getId())
                .tag(todoTag.getTag())
                .build();

        return new AppResponse<>(HttpStatus.OK.value(), TAG_FETCHED_SUCCESS, response, null);
    }

    @Override
    public AppResponse<List<TodoTagsResponse>> getAllTags() {

        List<TodoTag> todoTags = todoTagsRepository.findAll();

        List<TodoTagsResponse> todoTagsResponses = todoTags.stream()
                .map(todoTag -> TodoTagsResponse.builder()
                        .id(todoTag.getId())
                        .tag(todoTag.getTag())
                        .build())
                .toList();

        return new AppResponse<>(HttpStatus.OK.value(), TAG_FETCHED_SUCCESS, todoTagsResponses, null);
    }

    @Override
    public AppResponse<TodoTagsResponse> updateTag(String tag) {

        TodoTag todoTag = todoTagsRepository.findByTag(tag)
                .orElseThrow(() -> new NotFoundException(TAG_NOT_FOUND));

        todoTag.setTag(tag);
        TodoTag updatedTag = todoTagsRepository.save(todoTag);

        TodoTagsResponse response = TodoTagsResponse.builder()
                .id(updatedTag.getId())
                .tag(updatedTag.getTag())
                .build();

        return new AppResponse<>(HttpStatus.OK.value(), TAG_UPDATED_SUCCESS, response, null);
    }

    @Override
    public AppResponse<String> deleteTag(String tag) {

        TodoTag todoTag = todoTagsRepository.findByTag(tag)
                .orElseThrow(() -> new NotFoundException(TAG_NOT_FOUND));

        todoTag.setStatus(Status.DELETED);
        todoTagsRepository.save(todoTag);
        return new AppResponse<>(HttpStatus.OK.value(), TAG_DELETED_SUCCESS, null, null);
    }


}
