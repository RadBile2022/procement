package com.enigma.eprocurement.user.domain.repository;

import com.enigma.eprocurement.user.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}

