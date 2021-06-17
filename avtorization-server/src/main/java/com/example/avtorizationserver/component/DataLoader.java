package com.example.avtorizationserver.component;

import com.example.avtorizationserver.entity.Role;
import com.example.avtorizationserver.entity.User;
import com.example.avtorizationserver.entity.enums.RoleName;
import com.example.avtorizationserver.repository.RoleRepository;
import com.example.avtorizationserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initMode;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository,
                      @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {

            Role admin = roleRepository.save(new Role(RoleName.ROLE_SUPER_ADMIN));

            Role client = roleRepository.save(new Role(RoleName.ROLE_CLIENT));

            userRepository.save(
                    new User(
                            "Super_admin",
                            "Adminov",
                            "Adminovich",
                            "+998977117711",
                            "admin@gmail.com",
                            passwordEncoder.encode("root123"),
                            Collections.singleton(admin),
                            true
                    )
            );
            userRepository.save(
                    new User(
                            "Client",
                            "Clientov",
                            "Clientovich",
                            "+998992344565",
                            "client@gmail.com",
                            passwordEncoder.encode("root123"),
                            Collections.singleton(client),
                            true
                    )
            );
        }
    }
}
