package com.example.procementapi.domain.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VendorRequest {

    @NotBlank(message = "vendor id is required")
    private String id;

    private String name;

    private String mobilePhone;

    private String address;
}
