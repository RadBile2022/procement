package com.example.procementapi.domain.repositories;

import com.example.procementapi.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
}
