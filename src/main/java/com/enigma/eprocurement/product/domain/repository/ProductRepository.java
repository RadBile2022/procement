package com.enigma.eprocurement.product.domain.repository;

import com.enigma.eprocurement.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    Page<Product> findAllByIsDeleteFalse(Boolean isDelete, Specification<Product> specification, Pageable pageable);
}
