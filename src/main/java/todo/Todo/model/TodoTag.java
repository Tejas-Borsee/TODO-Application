package todo.Todo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import todo.common.model.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Audited
@Table(name = "todo_tag")
@AuditTable(value = "todo_tags_audit_table")
@SQLRestriction("status <> 'DELETED'")
public class TodoTag extends BaseEntity {

    @Column(name = "tag")
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<Todo> todos = new HashSet<>();
}
