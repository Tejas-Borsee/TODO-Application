package todo.Users.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import todo.Todo.model.Todo;
import todo.common.model.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@AuditTable(value = "users_audit_log")
@SQLRestriction("status <> 'DELETED'")
public class Users extends BaseEntity {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

}
