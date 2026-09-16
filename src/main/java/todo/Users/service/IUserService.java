package todo.Users.service;

import todo.Users.domain.request.UserCreateRequest;
import todo.Users.domain.request.UserUpdateRequest;
import todo.Users.domain.response.UserResponse;
import todo.common.domain.response.AppResponse;

import java.util.List;

public interface IUserService {

    AppResponse<UserResponse> createUser(UserCreateRequest userCreateRequest);

    AppResponse<UserResponse> fetchUser(String userId);

    AppResponse<List<UserResponse>> fetchAllUsers();

    AppResponse<UserResponse> updateUser(String userId, UserUpdateRequest userUpdateRequest);

    AppResponse<String> deleteUser(String userId);
}
