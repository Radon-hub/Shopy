package org.radon.shopy.integration.vendor.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorAddressResponseDto {
    private String title;
    private String province;
    private String city;
    private String area;
    private String street;
    private String number;
}
