package com.example.avtorizationserver.payload;

import com.example.avtorizationserver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID userId;

    private String firstName;

    private String lastName;

    private String surName;

    private String phoneNumber;

    private String userName;

    private String password;

    private String email;

    public UserDto(String firstName, String lastName, String surName, String phoneNumber, String email, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.email = email;
    }

}
