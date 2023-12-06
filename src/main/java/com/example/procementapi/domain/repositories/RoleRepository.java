package com.example.procementapi.domain.repositories;

import com.example.procementapi.entities.Role;
import com.example.procementapi.entities.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByRole(ERole erole);
}
