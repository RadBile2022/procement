package com.enigma.eprocurement.product.domain.services.abst;

import com.enigma.eprocurement.product.domain.models.request.ProductRequest;
import com.enigma.eprocurement.product.domain.models.response.ProductResponse;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);
    List<ProductResponse> createBulk(List<ProductRequest> products);
    ProductResponse getById(String id);
    Page<ProductResponse> getAllByNameOrPrice(String name, Long price, Integer page, Integer size);
    ProductResponse update(ProductRequest request);
    void hardDeleteById(String id);
    void softDeleteById(String id);

}
