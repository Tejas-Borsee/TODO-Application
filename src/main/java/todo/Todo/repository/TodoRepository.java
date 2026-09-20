package todo.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.Todo.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {


}
