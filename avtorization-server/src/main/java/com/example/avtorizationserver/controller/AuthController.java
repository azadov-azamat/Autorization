package com.example.avtorizationserver.controller;


import com.example.avtorizationserver.component.MessageByLang;
import com.example.avtorizationserver.payload.ApiResponse;
import com.example.avtorizationserver.payload.LoginDto;
import com.example.avtorizationserver.payload.UserDto;
import com.example.avtorizationserver.repository.UserRepository;
import com.example.avtorizationserver.security.JwtTokenProvider;
import com.example.avtorizationserver.service.AuthService;
import com.example.avtorizationserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.jetbrains.annotations.NotNull;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    MessageByLang messageByLang;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(getJwt(loginDto));
    }

    public String getJwt(@NotNull LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getPhoneNumber(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.registerClient(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
}
