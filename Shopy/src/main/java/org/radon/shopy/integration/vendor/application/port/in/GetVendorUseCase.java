package org.radon.shopy.integration.vendor.application.port.in;

import org.radon.shopy.integration.vendor.domain.model.Vendor;
import org.radon.shopy.integration.vendor.presentation.dto.VendorResponseDto;

public interface GetVendorUseCase {
    Vendor getVendor(Long id);
}
