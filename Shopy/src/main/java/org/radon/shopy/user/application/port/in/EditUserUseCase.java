package org.radon.shopy.user.application.port.in;

import org.radon.shopy.shared.aop.exceptionHandling.model.UserNotFoundException;
import org.radon.shopy.user.domain.model.User;

public interface EditUserUseCase {
    User editUser(User user) throws UserNotFoundException;
}
