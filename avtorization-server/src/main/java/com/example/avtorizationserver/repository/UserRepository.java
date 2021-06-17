package com.example.avtorizationserver.repository;


import com.example.avtorizationserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

    User findByPhoneNumber(String phoneNumber);

    User findByUserName(String userName);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);
}
