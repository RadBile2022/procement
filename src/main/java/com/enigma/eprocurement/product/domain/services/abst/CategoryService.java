package com.enigma.eprocurement.product.domain.services.abst;


import com.enigma.eprocurement.product.entity.Category;

public interface CategoryService {

    Category getOrSave(String name);

}
