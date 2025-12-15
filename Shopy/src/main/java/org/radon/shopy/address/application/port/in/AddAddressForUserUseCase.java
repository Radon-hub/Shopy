package org.radon.shopy.address.application.port.in;

import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.address.presentation.dto.AddAddressRequest;
import org.radon.shopy.shared.aop.exceptionHandling.model.UserNotFoundException;

public interface AddAddressForUserUseCase {
    Address addAddressForUser(AddAddressRequest addAddressRequest);
}
