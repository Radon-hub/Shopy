package org.radon.shopyvendor.auth.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorLoginRequest {
    private String phoneNumber;
    private String password;
}
