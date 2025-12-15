package org.radon.shopyvendor.auth.application.port.out;

import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthRepository extends UserDetailsService {
    String createVendor(Vendor vendor);
    Vendor getVendorWithPhoneNumber(String phoneNumber);
}
