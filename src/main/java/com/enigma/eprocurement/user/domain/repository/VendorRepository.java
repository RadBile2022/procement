package com.enigma.eprocurement.user.domain.repository;

import com.enigma.eprocurement.user.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, String> {
}
