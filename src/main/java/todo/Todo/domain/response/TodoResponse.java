package todo.Todo.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import todo.Todo.domain.enums.Priority;
import todo.Todo.domain.enums.TodoStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {

    private String title;
    private Priority priority;
    private TodoStatus todoStatus;
    private String dueDate;
    private String estimatedTime;
    private String actualTimeTaken;
    private String user;
    private String todoTag;

}
