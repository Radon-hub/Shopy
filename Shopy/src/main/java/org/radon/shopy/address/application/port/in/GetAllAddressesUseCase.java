package org.radon.shopy.address.application.port.in;

import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.address.presentation.dto.AddressResponse;

import java.util.List;

public interface GetAllAddressesUseCase {
    List<Address> returnAllAddresses();
}
