package org.radon.shopy.address.presentation.dto;

public record AddAddressRequest(
        String title,
        String province,
        String city,
        String area,
        String street,
        String extraDetails
) {
}
