package org.radon.shopy.user.application.port.in;

import org.radon.shopy.shared.aop.exceptionHandling.model.UserNotFoundException;
import org.radon.shopy.shared.dto.PagedResponse;
import org.radon.shopy.user.domain.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface GetAllUsersUseCase {
    PagedResponse<User> getAllUsers(@PageableDefault(
            size = 5,
            page = 1
    ) Pageable pageable) throws UserNotFoundException;
}
