package todo.Security.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import todo.Users.constants.UserConstants;
import todo.Users.model.Users;
import todo.Users.repository.UserRepository;
import todo.common.exception.NotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrMobile) throws UsernameNotFoundException {

        Users user = userRepository.findByEmailOrMobile(emailOrMobile, emailOrMobile)
                .orElseThrow(() -> new NotFoundException(UserConstants.USER_NOT_FOUND));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getMobile())
                .password(user.getPassword())
                .build();
    }


}
