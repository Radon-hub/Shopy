package org.radon.shopy.user.application.port.out;

import org.radon.shopy.shared.aop.exceptionHandling.model.*;
import org.radon.shopy.shared.dto.PagedResponse;
import org.radon.shopy.user.domain.model.ResetPassword;
import org.radon.shopy.user.domain.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface UserRepository {
    User getUser();
    User editUser(User user);
    PagedResponse<User> getAllUsers(@PageableDefault(size = 5, page = 1) Pageable pageable);
    User getUserByEmailOrPhoneNumber(String email, String phoneNumber);
    String editPassword(ResetPassword resetPassword);
}
