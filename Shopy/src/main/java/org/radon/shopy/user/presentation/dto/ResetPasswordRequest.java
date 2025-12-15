package org.radon.shopy.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
    private String phoneNumber;
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
}
