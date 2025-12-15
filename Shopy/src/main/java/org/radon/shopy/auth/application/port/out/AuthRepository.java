package org.radon.shopy.auth.application.port.out;

import org.radon.shopy.user.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthRepository extends UserDetailsService {
    User createUser(User user);
    UserDetails loadUserByUsername(String username);
}
