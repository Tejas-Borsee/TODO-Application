package todo.Users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import todo.common.model.BaseEntity;

@Getter
@Setter
@Entity
@Builder
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@AuditTable(value = "users_audit_log")
public class Users extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

}
