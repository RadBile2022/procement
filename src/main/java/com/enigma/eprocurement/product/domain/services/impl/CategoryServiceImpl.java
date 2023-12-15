package com.enigma.eprocurement.product.domain.services.impl;

import com.enigma.eprocurement.product.domain.repository.CategoryRepository;
import com.enigma.eprocurement.product.domain.services.abst.CategoryService;
import com.enigma.eprocurement.product.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getOrSave(String name) {
        return categoryRepository.findByName(name).orElseGet(() -> categoryRepository.saveAndFlush(Category.builder().name(name).build()));
    }
}
