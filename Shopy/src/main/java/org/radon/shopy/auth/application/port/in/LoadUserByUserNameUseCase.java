package org.radon.shopy.auth.application.port.in;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoadUserByUserNameUseCase {
    UserDetails loadUserByUsername(String username);
}
