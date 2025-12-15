package org.radon.shopy.user.application.port.in;

import org.radon.shopy.shared.aop.exceptionHandling.model.*;
import org.radon.shopy.user.domain.model.ResetPassword;
import org.radon.shopy.user.domain.model.User;

public interface EditPasswordUseCase {
    String editPassword(ResetPassword resetPassword) throws CredentialException,UserNotFoundException, PasswordNotMatchException, PasswordOldNotMatchException, PasswordCanNotBeSameException;
}
