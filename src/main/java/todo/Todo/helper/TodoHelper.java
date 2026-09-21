package todo.Todo.helper;

import todo.Todo.domain.response.TodoResponse;
import todo.Todo.domain.response.TodoTagsResponse;
import todo.Todo.model.Todo;
import todo.Todo.model.TodoTag;
import todo.Users.domain.response.UserResponse;
import todo.Users.model.Users;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TodoHelper {

    private TodoHelper(){
    }

    public static TodoResponse buildTodoResponse(Todo todo){

        Set<TodoTagsResponse> tagsResponses = todo.getTags()
                .stream()
                .map(tag -> TodoTagsResponse.builder()
                        .id(tag.getId())
                        .tag(tag.getTag())
                        .build())
                .collect(Collectors.toSet());

        Users user = todo.getUser();
        UserResponse userResponse = UserResponse.builder()
                .createdAt(String.valueOf(user.getCreatedAt()))
                .id(user.getId())
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .status(String.valueOf(user.getStatus()))
                .build();

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .priority(todo.getPriority())
                .todoStatus(todo.getTodoStatus())
                .dueDate(String.valueOf(todo.getDueDate()))
                .estimatedTime(String.valueOf(todo.getEstimatedTime()))
                .actualTimeTaken(String.valueOf(todo.getActualTimeTaken()))
                .user(userResponse)
                .todoTagsResponses(tagsResponses)
                .build();
    }

    public static List<TodoResponse> buildTodoResponse(List<Todo> todos){
        return null;
    }

}
