package todo.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.Todo.model.TodoTag;

import java.util.Optional;

@Repository
public interface TodoTagsRepository extends JpaRepository<TodoTag, String> {

    Optional<TodoTag> findByTag(String tag);

    boolean existsByTag(String tag);

}
