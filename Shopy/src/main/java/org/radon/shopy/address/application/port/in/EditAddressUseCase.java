package org.radon.shopy.address.application.port.in;

import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.address.presentation.dto.AddAddressRequest;

public interface EditAddressUseCase {
    Address editAddress(Long id,AddAddressRequest addAddressRequest);
}
