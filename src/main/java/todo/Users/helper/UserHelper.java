package todo.Users.helper;


import todo.Users.domain.response.UserResponse;
import todo.Users.model.Users;

import java.util.List;
import java.util.stream.Collectors;

public class UserHelper {

    private UserHelper(){
    }

    public static UserResponse buildUserResponse(Users users){
        return UserResponse.builder()
                .createdAt(String.valueOf(users.getCreatedAt()))
                .id(users.getId())
                .userId(users.getUserId())
                .username(users.getUsername())
                .email(users.getEmail())
                .mobile(users.getMobile())
                .status(String.valueOf(users.getStatus()))
                .build();
    }

    public static List<UserResponse> buildUserResponse(List<Users> users){
        if (users.isEmpty()){
            return List.of();
        }

        return users.stream()
                .map(user -> UserResponse.builder()
                        .createdAt(String.valueOf(user.getCreatedAt()))
                        .id(user.getId())
                        .userId(user.getUserId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .mobile(user.getMobile())
                        .status(String.valueOf(user.getStatus()))
                        .build())
                .collect(Collectors.toList());
    }
}
