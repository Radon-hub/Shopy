package org.radon.shopyvendor.vendor.application.port.out;

import org.radon.shopyvendor.vendor.domain.model.Vendor;

public interface VendorRepository {
    String deleteVendor(Vendor vendor);
    Vendor editVendor(Vendor vendor);
    Vendor getVendor(Long id);
}
