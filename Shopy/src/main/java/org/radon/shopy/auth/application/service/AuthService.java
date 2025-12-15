package org.radon.shopy.auth.application.service;

import org.radon.shopy.auth.application.port.in.CreateUserUseCase;
import org.radon.shopy.auth.application.port.in.GetCurrentUserUseCase;
import org.radon.shopy.auth.application.port.in.LoadUserByUserNameUseCase;
import org.radon.shopy.auth.application.port.out.AuthRepository;
import org.radon.shopy.events.application.port.in.SendUserRegisteredEventUseCase;
import org.radon.shopy.events.domain.model.UserRegisteredEvent;
import org.radon.shopy.user.domain.model.Password;
import org.radon.shopy.user.domain.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService implements CreateUserUseCase, LoadUserByUserNameUseCase,GetCurrentUserUseCase {

    private final AuthRepository authRepository;
    private final SendUserRegisteredEventUseCase sendUserRegisteredEventUseCase;

    public AuthService(AuthRepository authRepository, SendUserRegisteredEventUseCase sendUserRegisteredEventUseCase) {
        this.authRepository = authRepository;
        this.sendUserRegisteredEventUseCase = sendUserRegisteredEventUseCase;
    }

    @Override
    public User createUser(User user){

        User register = authRepository.createUser(user);

        sendUserRegisteredEventUseCase.sendEvent(new UserRegisteredEvent(
                register.getId(),
                register.getFullName().value(),
                register.getEmail().value(),
                register.getPhoneNumber().value(),
                LocalDateTime.now().toString()
        ));

        return register;

    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return authRepository.loadUserByUsername(username);
    }

    @Override
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
