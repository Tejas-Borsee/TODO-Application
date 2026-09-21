package todo.Todo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import todo.Todo.domain.enums.Priority;
import todo.Todo.domain.enums.TodoStatus;
import todo.Users.model.Users;
import todo.common.model.BaseEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo")
@AuditTable(value = "todo_audit_log")
@SQLRestriction("status <> 'DELETED'")
public class Todo extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "Priority")
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "todo_status")
    private TodoStatus todoStatus;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "estimated_time")
    private LocalDate estimatedTime;

    @Column(name = "actual_time_taken")
    private LocalDate actualTimeTaken;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToMany
    @Builder.Default
    @JoinTable(name = "todo_tags", joinColumns = @JoinColumn(name = "todo_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TodoTag> tags = new HashSet<>();

}
