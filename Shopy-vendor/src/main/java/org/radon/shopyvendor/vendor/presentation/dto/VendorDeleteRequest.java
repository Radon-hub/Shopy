package org.radon.shopyvendor.vendor.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.radon.shopyvendor.vendor.domain.model.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDeleteRequest {
    private String phoneNumber;
    private String password;
    private String passwordConfirm;
}
