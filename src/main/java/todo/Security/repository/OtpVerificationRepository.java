package todo.Security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todo.Security.model.OtpVerification;

@Repository
public interface OtpVerificationRepository extends JpaRepository<OtpVerification, String> {
}
