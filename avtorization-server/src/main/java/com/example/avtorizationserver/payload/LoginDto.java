package com.example.avtorizationserver.payload;


import lombok.Data;

@Data
public class LoginDto {

    private String phoneNumber;

    private String password;
}
