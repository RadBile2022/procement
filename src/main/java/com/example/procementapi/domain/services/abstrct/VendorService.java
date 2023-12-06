package com.example.procementapi.domain.services.abstrct;

import com.example.procementapi.domain.models.requests.VendorRequest;
import com.example.procementapi.domain.models.responses.VendorResponse;
import com.example.procementapi.entities.Vendor;
import org.springframework.data.domain.Page;

public interface VendorService {
    Vendor create (Vendor vendor);

    Vendor getById(String id);
    Page<VendorResponse> getAll(Integer page, Integer size);
    VendorResponse update (VendorRequest request);
}
