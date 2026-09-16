package todo.Users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.Users.domain.request.UserCreateRequest;
import todo.Users.domain.request.UserUpdateRequest;
import todo.Users.domain.response.UserResponse;
import todo.Users.service.IUserService;
import todo.common.constants.ApiConstants;
import todo.common.domain.response.AppResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.BASE_URI + ApiConstants.ADMIN_URI + ApiConstants.USER_URI)
public class UserController {

    private final IUserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<AppResponse<UserResponse>> createUser(@RequestBody UserCreateRequest userCreateRequest){
        return ResponseEntity.ok(userService.createUser(userCreateRequest));
    }

    @GetMapping("/fetch-user")
    public ResponseEntity<AppResponse<UserResponse>> fetchUser(@RequestParam String userId){
        return ResponseEntity.ok(userService.fetchUser(userId));
    }

    @GetMapping("/fetch-all-users")
    public ResponseEntity<AppResponse<List<UserResponse>>> fetchAllUsers(){
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @PutMapping("/update-user")
    public ResponseEntity<AppResponse<UserResponse>> updateUser(@RequestParam String userId, @RequestBody UserUpdateRequest userUpdateRequest){
        return ResponseEntity.ok(userService.updateUser(userId, userUpdateRequest));
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<AppResponse<String>> deleteUser(@RequestParam String userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
