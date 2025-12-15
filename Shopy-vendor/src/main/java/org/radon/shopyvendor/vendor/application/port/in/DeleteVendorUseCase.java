package org.radon.shopyvendor.vendor.application.port.in;

import org.radon.shopyvendor.vendor.domain.model.Vendor;

public interface DeleteVendorUseCase {
    String deleteCall(Vendor vendor);
}
