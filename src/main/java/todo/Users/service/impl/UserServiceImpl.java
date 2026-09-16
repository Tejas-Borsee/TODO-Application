package todo.Users.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import todo.Users.domain.request.UserCreateRequest;
import todo.Users.domain.response.UserResponse;
import todo.Users.helper.UserHelper;
import todo.Users.model.Users;
import todo.Users.repository.UserRepository;
import todo.Users.service.IUserService;
import todo.common.domain.enums.Status;
import todo.common.domain.response.AppResponse;
import todo.common.exception.NotFoundException;
import todo.common.exception.UnprocessableException;

import java.util.Objects;

import static todo.Users.constants.UserConstants.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public AppResponse<UserResponse> createUser(UserCreateRequest request) {

        Users userExists = userRepository.findByUsernameOrEmailOrMobileAndStatus(request.getUsername(), request.getEmail(), request.getMobile(), Status.ACTIVE);
        if (Objects.nonNull(userExists)){
            throw new UnprocessableException(USER_ALREADY_EXISTS);
        }

        Users user = Users.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .build();
        Users savedUser = userRepository.save(user);
        UserResponse userResponse = UserHelper.buildUserResponse(savedUser);

        return new AppResponse<>(HttpStatus.OK.value(), USER_REGISTERED_SUCCESS, userResponse, null);
    }
}
