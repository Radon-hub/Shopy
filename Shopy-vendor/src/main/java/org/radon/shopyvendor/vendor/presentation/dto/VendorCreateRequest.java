package org.radon.shopyvendor.vendor.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.radon.shopyvendor.vendor.domain.model.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorCreateRequest {
    private String name;
    private String description;
    private String phoneNumber;
    private Address address;
    private String password;
}
