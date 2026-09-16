package todo.Users.service;

import todo.Users.domain.request.UserCreateRequest;
import todo.Users.domain.response.UserResponse;
import todo.common.domain.response.AppResponse;

public interface IUserService {

    AppResponse<UserResponse> createUser(UserCreateRequest userCreateRequest);
}
