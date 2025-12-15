package org.radon.shopyvendor.auth.application.port.in;

import org.radon.shopyvendor.vendor.domain.model.Vendor;

public interface GetVendorByPhoneNumber {
    Vendor getByPhoneCall(String phoneNumber);
}
