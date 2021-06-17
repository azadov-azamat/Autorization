package com.example.avtorizationserver.service;

import com.example.avtorizationserver.payload.ApiResponse;
import com.example.avtorizationserver.payload.UserDto;
import com.example.avtorizationserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public UserDetails loadUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User id not found: " + userId));
    }

    public ApiResponse checkPassword(UserDto userDto) {
        if (userDto.getPassword().length() < 6 || userDto.getPassword().length() > 16)
            return new ApiResponse("Password size between 6 and 16 character!", false);
        return new ApiResponse("", true);
    }
}
