package com.enigma.eprocurement.product.domain.services.abst;


import com.enigma.eprocurement.product.entity.ProductPrice;

public interface ProductPriceService {

    ProductPrice create(ProductPrice productPrice);
    ProductPrice getById(String id);
    ProductPrice findProductPriceActive(String productId, Boolean active);
}
