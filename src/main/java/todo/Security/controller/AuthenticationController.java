package todo.Security.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.Security.domain.request.LoginRequest;
import todo.Security.domain.response.LoginResponse;
import todo.Security.service.IAuthenticationService;
import todo.Users.domain.request.UserCreateRequest;
import todo.Users.domain.response.UserResponse;
import todo.Users.service.IUserService;
import todo.common.constants.ApiConstants;
import todo.common.domain.response.AppResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.BASE_URI + ApiConstants.AUTHENTICATION_URI)
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    private final IUserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<AppResponse<UserResponse>> createUser(@RequestBody UserCreateRequest userCreateRequest){
        return ResponseEntity.ok(userService.createUser(userCreateRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AppResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        log.info("AuthenticationController - Inside logOut method");
        return ResponseEntity.ok(authenticationService.logout(request));
    }
}
