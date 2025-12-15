package org.radon.shopyvendor.vendor.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.vendor.domain.model.Address;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponse {
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
    private Address address;
}
