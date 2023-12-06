package com.example.procementapi.domain.repositories;

import com.example.procementapi.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,String> {
}
