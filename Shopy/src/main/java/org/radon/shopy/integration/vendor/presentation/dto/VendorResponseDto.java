package org.radon.shopy.integration.vendor.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponseDto {
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
    private VendorAddressResponseDto address;
}
