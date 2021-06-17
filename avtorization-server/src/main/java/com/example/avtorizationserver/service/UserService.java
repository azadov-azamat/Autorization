package com.example.avtorizationserver.service;


import com.example.avtorizationserver.component.MessageByLang;
import com.example.avtorizationserver.controller.AuthController;
import com.example.avtorizationserver.entity.User;
import com.example.avtorizationserver.entity.enums.RoleName;
import com.example.avtorizationserver.payload.ApiResponse;
import com.example.avtorizationserver.payload.LoginDto;
import com.example.avtorizationserver.payload.UserDto;
import com.example.avtorizationserver.repository.RoleRepository;
import com.example.avtorizationserver.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService {

    final PasswordEncoder passwordEncoder;

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final MessageByLang messageByLang;

    final AuthController authController;

    final AuthService authService;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, MessageByLang messageByLang, AuthController authController, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.messageByLang = messageByLang;
        this.authController = authController;
        this.authService = authService;
    }


    public ApiResponse registerClient(UserDto userDto) {
        try {
            User byPhoneNumber = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
            if (byPhoneNumber != null)
                return new ApiResponse("This phone number is already registered", false);

            User byEmail = userRepository.findByEmail(userDto.getEmail());
            if (byEmail != null)
                return new ApiResponse("This email is already registered", false);
            ApiResponse checkUserNames = checkUserNames(userDto);
            if (!checkUserNames.isSuccess())
                return checkUserNames;
            ApiResponse apiResponse = makePhoneNumberPattern(userDto.getPhoneNumber());
            if (apiResponse.isSuccess()) {
                User user = new User();
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setSurName(userDto.getSurName());
                if (userDto.getUserName() != null) {
                    User byUserName = userRepository.findByUserName(userDto.getUserName());
                    if (byUserName != null)
                        return new ApiResponse("This username is already registered", false);
                    user.setUserName(user.getUsername());
                }
                user.setPhoneNumber(userDto.getPhoneNumber());
                ApiResponse randomPassword = generatePasswordForUser();
                if (userDto.getPassword() == null) {
                    user.setPassword(passwordEncoder.encode(randomPassword.getMessage()));
                } else {
                    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                }
                ApiResponse emailPattern = makeEmailPattern(userDto.getEmail());
                if (emailPattern.isSuccess()) {
                    user.setEmail(userDto.getEmail().toLowerCase());
                } else {
                    return emailPattern;
                }
                user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_CLIENT)));
                user.setEnabled(true);
                userRepository.save(user);

                LoginDto reqSignIn = new LoginDto();
                reqSignIn.setPhoneNumber(userDto.getPhoneNumber());
                reqSignIn.setPassword(randomPassword.getMessage());
                String jwt = authController.getJwt(reqSignIn);
                return new ApiResponse("Success", true, jwt);
            } else {
                return apiResponse;
            }
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }

    }

    public ApiResponse editPassword(UserDto userDto, User user) {
        ApiResponse checkPassword = authService.checkPassword(userDto);
        if (!checkPassword.isSuccess())
            return checkPassword;
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return new ApiResponse("Successfully edited", true);
    }

    /* ========== METHOD ========== */

    public ApiResponse checkUserNames(UserDto userDto) {
        if (userDto.getFirstName() == null || userDto.getLastName() == null || userDto.getSurName() == null) {
            return new ApiResponse("Iltimos Familya Ism Sharifingizni to`liq va namunadagidek to`ldiring", false);
        }
        return new ApiResponse("",true);
    }

    public ApiResponse makePhoneNumberPattern(String phoneNumber) {
        if (phoneNumber != null) {
            phoneNumber = phoneNumber.startsWith("+") ? phoneNumber : "+" + phoneNumber;
            String regex = "^[+][9][9][8][0-9]{9}$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phoneNumber);

            if (matcher.matches()) {
                return new ApiResponse(true);
            } else {
                return new ApiResponse("phone number error", false);
            }
        } else {
            return new ApiResponse("Enter phone number", false);
        }
    }

    public ApiResponse makeEmailPattern(String email) {

        try {
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(email);
            System.out.println(email + " : " + matcher.matches());

            return new ApiResponse("success email", true, matcher.matches());

        } catch (Exception e) {
            return new ApiResponse("error email", false);
        }

    }

    private ApiResponse generatePasswordForUser() {
        int total = 1;
        int length = 15;
        String[] randomPasswords = new String[total];
        for (int i = 0; i < total; i++) {
            String randomPassword = "";
            for (int j = 0; j < length; j++) {
                randomPassword += randomCharacter();
            }
            randomPasswords[i] = randomPassword;
        }
        printArray(randomPasswords);
        for (int i = 0; i < randomPasswords.length; i++) {
            return new ApiResponse(randomPasswords[i], true);
        }
        return null;
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static char randomCharacter() {
        int rand = (int) (Math.random() * 62);
        if (rand <= 9) {
            int number = rand + 48;
            return (char) (number);
        } else if (rand <= 35) {
            int uppercase = rand + 55;
            return (char) (uppercase);
        } else {
            int lowercase = rand + 61;
            return (char) (lowercase);
        }
    }

}
