package todo.Users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.Users.domain.request.UserCreateRequest;
import todo.Users.domain.response.UserResponse;
import todo.Users.service.IUserService;
import todo.common.constants.ApiConstants;
import todo.common.domain.response.AppResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.BASE_URI + ApiConstants.ADMIN_URI + ApiConstants.USER_URI)
public class UserController {

    private final IUserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<AppResponse<UserResponse>> createUser(@RequestBody UserCreateRequest userCreateRequest){
        return ResponseEntity.ok(userService.createUser(userCreateRequest));
    }


}
