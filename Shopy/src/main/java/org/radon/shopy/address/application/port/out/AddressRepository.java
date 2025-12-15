package org.radon.shopy.address.application.port.out;

import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.address.presentation.dto.AddAddressRequest;
import org.radon.shopy.address.presentation.dto.AddressResponse;
import org.radon.shopy.shared.aop.exceptionHandling.model.UserNotFoundException;

import java.util.List;

public interface AddressRepository {
    List<Address> returnAllAddresses();
    Address editAddress(Long id,AddAddressRequest addAddressRequest);
    Address createAddressForUser(AddAddressRequest addAddressRequest);
}
