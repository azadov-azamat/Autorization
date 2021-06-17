package com.example.avtorizationserver.controller;

import com.example.avtorizationserver.entity.User;
import com.example.avtorizationserver.payload.ApiResponse;
import com.example.avtorizationserver.payload.UserDto;
import com.example.avtorizationserver.security.CurrentUser;
import com.example.avtorizationserver.security.JwtTokenProvider;
import com.example.avtorizationserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

   @Autowired
   JwtTokenProvider jwtTokenProvider;

   @Autowired
   UserService userService;


    @GetMapping("/me")
    public HttpEntity<?> me(@CurrentUser User user) {
        return ResponseEntity.status(user != null ? 200 : 409).body(user);
    }

    @PutMapping("/editPassword")
    public HttpEntity<?> editPassword(@RequestBody UserDto userDto, @CurrentUser User user) {
        ApiResponse response = userService.editPassword(userDto, user);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
