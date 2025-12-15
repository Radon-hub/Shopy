package org.radon.shopy.user.application.port.in;

import org.radon.shopy.shared.aop.exceptionHandling.model.UserNotFoundException;
import org.radon.shopy.user.domain.model.User;

public interface GetUserByEmailOrPhoneUseCase {
    User getUserByEmailOrPhoneNumber(String email, String phoneNumber) throws UserNotFoundException;
}
