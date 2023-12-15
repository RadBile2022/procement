package com.enigma.eprocurement.user.domain.repository;

import com.enigma.eprocurement.user.entity.Role;
import com.enigma.eprocurement.user.entity.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByRole(ERole role);

}
