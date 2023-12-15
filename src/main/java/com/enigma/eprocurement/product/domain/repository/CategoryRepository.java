package com.enigma.eprocurement.product.domain.repository;

import com.enigma.eprocurement.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(String name);
}
