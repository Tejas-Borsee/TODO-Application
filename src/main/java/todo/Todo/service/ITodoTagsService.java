package todo.Todo.service;

import todo.Todo.domain.request.TodoTagsRequest;
import todo.Todo.domain.response.TodoTagsResponse;
import todo.common.domain.response.AppResponse;

import java.util.List;
import java.util.Optional;

public interface ITodoTagsService {

    AppResponse<TodoTagsResponse> createTag(TodoTagsRequest todoTagsRequest);

    AppResponse<TodoTagsResponse> getTag(String tag);

    AppResponse<List<TodoTagsResponse>> getAllTags();

    AppResponse<TodoTagsResponse> updateTag(String id, String tag);

    AppResponse<String> deleteTag(String id);
}
