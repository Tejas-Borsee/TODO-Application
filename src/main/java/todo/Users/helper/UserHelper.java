package todo.Users.helper;


import todo.Users.domain.response.UserResponse;
import todo.Users.model.Users;

public class UserHelper {

    private UserHelper(){
    }

    public static UserResponse buildUserResponse(Users users){
        return UserResponse.builder()
                .createdAt(String.valueOf(users.getCreatedAt()))
                .id(users.getId())
                .username(users.getUsername())
                .email(users.getEmail())
                .mobile(users.getMobile())
                .status(String.valueOf(users.getStatus()))
                .build();
    }
}
