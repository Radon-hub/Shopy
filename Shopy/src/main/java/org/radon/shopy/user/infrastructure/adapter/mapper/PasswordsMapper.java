package org.radon.shopy.user.infrastructure.adapter.mapper;

import org.radon.shopy.user.domain.model.ResetPassword;
import org.radon.shopy.user.presentation.dto.ResetPasswordRequest;

public class PasswordsMapper {

    public static ResetPasswordRequest fromResetPassword(ResetPassword resetPassword) {
        return new ResetPasswordRequest(
                resetPassword.getPhoneNumber(),
                resetPassword.getOldPassword(),
                resetPassword.getNewPassword(),
                resetPassword.getNewPasswordConfirm()
        );
    }


    public static ResetPassword fromResetPasswordRequest(ResetPasswordRequest resetPasswordRequest) {
        return new ResetPassword(
                resetPasswordRequest.getPhoneNumber(),
                resetPasswordRequest.getOldPassword(),
                resetPasswordRequest.getNewPassword(),
                resetPasswordRequest.getNewPasswordConfirm()
        );
    }



}
