package org.radon.shopyvendor.vendor.application.port.in;

import org.radon.shopyvendor.vendor.domain.model.Vendor;

public interface EditVendorUseCase {
    Vendor editCall(Vendor vendor);
}
