package org.radon.shopy.address.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private String title;
    private String province;
    private String city;
    private String area;
    private String street;
    private String extraDetails;
}
