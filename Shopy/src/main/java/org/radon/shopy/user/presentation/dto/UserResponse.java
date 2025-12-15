package org.radon.shopy.user.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.radon.shopy.address.presentation.dto.AddressResponse;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String fullName;
    private String email;
    private String phoneNumber;
    private List<AddressResponse> addresses;
    private Byte age;
}
