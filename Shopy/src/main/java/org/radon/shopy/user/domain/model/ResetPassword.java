package org.radon.shopy.user.domain.model;

import org.radon.shopy.shared.aop.exceptionHandling.model.CredentialException;
import org.radon.shopy.shared.aop.exceptionHandling.model.PasswordNotMatchException;

public class ResetPassword {
    private String phoneNumber;
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;

    public ResetPassword(String phoneNumber, String oldPassword, String newPassword, String newPasswordConfirm) {
        this.phoneNumber = phoneNumber;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public void validatePasswords() throws CredentialException, PasswordNotMatchException {
        if(oldPassword.length() < 6){
            throw new CredentialException("Old password length should be at least 6 characters");
        }
        if(newPassword.length() < 6){
            throw new CredentialException("New password length should be at least 6 characters");
        }
        if(newPasswordConfirm.length() < 6){
            throw new CredentialException("Confirm password length should be at least 6 characters");
        }
        if(!checkNewPasswordsAreSame()){
            throw new PasswordNotMatchException();
        }
    }

    public boolean checkNewPasswordsAreSame(){
        return newPassword.equals(newPasswordConfirm);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}
