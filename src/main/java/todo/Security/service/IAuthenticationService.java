package todo.Security.service;

import jakarta.servlet.http.HttpServletRequest;
import todo.Security.domain.request.LoginRequest;
import todo.Security.domain.response.LoginResponse;
import todo.common.domain.response.AppResponse;

public interface IAuthenticationService {

    AppResponse<LoginResponse> login(LoginRequest loginRequest);

    String logout(HttpServletRequest request);
}
