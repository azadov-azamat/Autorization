package com.example.avtorizationserver.repository;


import com.example.avtorizationserver.entity.Role;
import com.example.avtorizationserver.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Set<Role> findAllByRoleName(RoleName roleName);

    Role findByRoleName(RoleName roleUser);
}
