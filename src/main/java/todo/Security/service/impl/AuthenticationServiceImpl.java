package todo.Security.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todo.Security.constants.AuthenticationConstants;
import todo.Security.domain.request.LoginRequest;
import todo.Security.domain.response.LoginResponse;
import todo.Security.service.IAuthenticationService;
import todo.Security.utils.JwtUtils;
import todo.Users.model.Users;
import todo.Users.repository.UserRepository;
import todo.common.domain.enums.Status;
import todo.common.domain.response.AppResponse;
import todo.common.exception.NotFoundException;

import static todo.Security.constants.AuthenticationConstants.USER_LOGIN_SUCCESS;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final SessionServiceImpl sessionService;

    @Override
    public AppResponse<LoginResponse> login(LoginRequest loginRequest) {

        Users user = userRepository.findByEmailOrMobile(loginRequest.getEmailOrMobile(), loginRequest.getEmailOrMobile())
                .orElseThrow(() -> new NotFoundException(AuthenticationConstants.INVALID_LOGIN_CREDENTIALS));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException(AuthenticationConstants.INVALID_PASSWORD);
        }

        if (user.getStatus().equals(Status.INACTIVE)) {
            throw new RuntimeException(AuthenticationConstants.USER_ACCOUNT_IS_DEACTIVATED);
        }

        String token = jwtUtils.generateToken(loginRequest.getEmailOrMobile(), user.getUserId());
        String jti = jwtUtils.extractJti(token);
        sessionService.createSession(loginRequest.getEmailOrMobile(), jti);
        LoginResponse loginResponse = LoginResponse.builder()
                .emailOrMobile(loginRequest.getEmailOrMobile())
                .accessToken(token)
                .tokenType("Bearer")
                .build();

        return new AppResponse<>(HttpStatus.OK.value(), USER_LOGIN_SUCCESS, loginResponse, null);
    }

    @Override
    public String logout(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return "Token is required";
        }

        String token = authorizationHeader.substring(7);

        try {

            String username = jwtUtils.extractUsername(token);
            String jti = jwtUtils.extractJti(token);
            sessionService.logout(username, jti);
            return "Logout successful";

        } catch (Exception e) {
            return "Invalid token";
        }
    }
}
