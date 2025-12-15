package org.radon.shopy.auth.application.port.in;

import org.radon.shopy.shared.aop.exceptionHandling.model.IllegalArgumentException;
import org.radon.shopy.shared.aop.exceptionHandling.model.UserExistException;
import org.radon.shopy.user.domain.model.User;

public interface CreateUserUseCase {
    User createUser(User user) throws UserExistException, IllegalArgumentException;
}
