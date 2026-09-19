package todo.Users.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todo.Users.domain.request.UserCreateRequest;
import todo.Users.domain.request.UserUpdateRequest;
import todo.Users.domain.response.UserResponse;
import todo.Users.helper.UserHelper;
import todo.Users.model.Users;
import todo.Users.repository.UserRepository;
import todo.Users.service.IUserService;
import todo.common.domain.enums.Status;
import todo.common.domain.response.AppResponse;
import todo.common.exception.NotFoundException;
import todo.common.exception.UnprocessableException;
import todo.common.utils.CommonUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static todo.Users.constants.UserConstants.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppResponse<UserResponse> createUser(UserCreateRequest request) {

        Users userExists = userRepository.findByUsernameOrEmailOrMobileAndStatus(request.getUsername(), request.getEmail(), request.getMobile(), Status.ACTIVE);
        if (Objects.nonNull(userExists)){
            throw new UnprocessableException(USER_ALREADY_EXISTS);
        }

        Users user = Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .mobile(request.getMobile())
                .userId(CommonUtils.generateUserId(3))
                .build();
        Users savedUser = userRepository.save(user);
        UserResponse userResponse = UserHelper.buildUserResponse(savedUser);

        return new AppResponse<>(HttpStatus.OK.value(), USER_REGISTERED_SUCCESS, userResponse, null);
    }

    @Override
    public AppResponse<UserResponse> fetchUser(String userId) {
        Users user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        UserResponse userResponse = UserHelper.buildUserResponse(user);
        return new AppResponse<>(HttpStatus.OK.value(), USER_FETCHED_SUCCESS, userResponse, null);
    }

    @Override
    public AppResponse<List<UserResponse>> fetchAllUsers() {
        List<Users> users = userRepository.findAll();
        List<UserResponse> userResponse = UserHelper.buildUserResponse(users);
        return new AppResponse<>(HttpStatus.OK.value(), USER_FETCHED_SUCCESS, userResponse, null);
    }

    @Override
    public AppResponse<UserResponse> updateUser(String userId, UserUpdateRequest request) {

        Users users = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        users.setUsername(request.getUsername());
        users.setEmail(request.getEmail());
        users.setMobile(request.getMobile());
        Users updateUser = userRepository.save(users);
        UserResponse userResponse = UserHelper.buildUserResponse(updateUser);
        return new AppResponse<>(HttpStatus.OK.value(), USER_UPDATED_SUCCESS, userResponse, null);
    }

    @Override
    public AppResponse<String> deleteUser(String userId) {
        Users users = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        users.setStatus(Status.DELETED);
        userRepository.save(users);
        return new AppResponse<>(HttpStatus.OK.value(), USER_DELETED_SUCCESS, null, null);
    }


}
