package todo.Users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.Users.model.Users;
import todo.common.domain.enums.Status;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findByEmailOrMobile(String email, String mobile);

    Optional<Users> findByUserIdAndStatus(String userId, Status status);

    Optional<Users> findByUserId(String userId);

    Users findByUsernameOrEmailOrMobileAndStatus(String username, String email, String mobile, Status status);

}
