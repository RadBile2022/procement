package com.example.procementapi.domain.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VendorResponse {
    private String id;
    private String name;
    private String address;
    private String mobilePhone;
}
