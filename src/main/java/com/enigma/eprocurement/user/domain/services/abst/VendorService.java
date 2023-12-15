package com.enigma.eprocurement.user.domain.services.abst;

import com.enigma.eprocurement.user.entity.Vendor;
import com.enigma.eprocurement.user.domain.model.request.VendorRequest;
import com.enigma.eprocurement.user.domain.model.response.VendorResponse;
import org.springframework.data.domain.Page;

public interface VendorService {

    Vendor create(Vendor vendor);

    Page<VendorResponse> getAll(Integer page, Integer size);

    Vendor getById(String id);

    VendorResponse update(VendorRequest request);




}
