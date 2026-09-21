package todo.Todo.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import todo.Todo.domain.enums.Priority;
import todo.Todo.domain.enums.TodoStatus;
import todo.Todo.model.TodoTag;
import todo.Users.domain.response.UserResponse;
import todo.Users.model.Users;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {

    private String id;
    private String title;
    private Priority priority;
    private TodoStatus todoStatus;
    private String dueDate;
    private String estimatedTime;
    private String actualTimeTaken;
    private UserResponse user;
    private Set<TodoTagsResponse> todoTagsResponses;

}
