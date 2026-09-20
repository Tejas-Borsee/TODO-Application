package todo.Todo.domain.request;

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
public class TodoRequest {

    private String title;
    private Priority priority;
    private TodoStatus todoStatus;
    private String dueDate;
    private String estimatedTime;
}
