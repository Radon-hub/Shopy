package org.radon.shopy.auth.presentation.dto;

public record UserLoginRequest(
        String email,
        String phoneNumber,
        String password
) {
}
