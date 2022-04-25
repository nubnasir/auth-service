package com.authentication.nubnasirauth.service;

import com.authentication.nubnasirauth.model.domain.UserEntity;
import com.authentication.nubnasirauth.model.dto.UserDto;
import com.authentication.nubnasirauth.repository.UserEntityRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder bcryptEncoder;

    public AuthUserDetailsService(UserEntityRepository userEntityRepository, PasswordEncoder bcryptEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUserName(), user.getPassword(),
                new ArrayList<>());
    }

    public UserDto save(String userName, String password, String email, String role, String status) {
        userEntityRepository.save(
                UserEntity.builder()
                        .userName(userName)
                        .password(bcryptEncoder.encode(password))
                        .email(email)
                        .userRole(role)
                        .status(status).build()
        );
        return new UserDto(userName, password);
    }
}