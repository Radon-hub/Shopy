package org.radon.shopyvendor.auth.application.port.in;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoadVendorByUserNameUseCase {
    UserDetails loadUserByUsername(String username);
}
