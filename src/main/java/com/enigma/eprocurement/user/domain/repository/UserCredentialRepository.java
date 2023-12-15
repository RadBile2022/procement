package com.enigma.eprocurement.user.domain.repository;

import com.enigma.eprocurement.user.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {

    Optional<UserCredential> findByEmail(String email);

}
