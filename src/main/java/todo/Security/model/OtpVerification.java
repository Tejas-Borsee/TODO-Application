package todo.Security.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opt_verification")
public class OtpVerification {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "otp_hash")
    private String otpHash;

    @Column(name = "expires_at")
    private String expiresAt;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "attempts")
    private int attempts;

}
