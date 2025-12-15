package org.radon.shopyvendor.auth.application.port.in;

import org.radon.shopyvendor.vendor.domain.model.Vendor;

public interface CreateVendorUseCase {
    String createCall(Vendor vendor);
}
