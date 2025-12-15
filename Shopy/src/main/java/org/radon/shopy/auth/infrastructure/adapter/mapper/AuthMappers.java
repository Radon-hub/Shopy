package org.radon.shopy.auth.infrastructure.adapter.mapper;

import org.radon.shopy.user.domain.model.*;
import org.radon.shopy.user.presentation.dto.UserCreateRequest;

public class AuthMappers {

    public static User toUser(UserCreateRequest req){
        return new User(
                null,
                new FullName(req.getFullName()),
                new Email(req.getEmail()),
                new PhoneNumber(req.getPhoneNumber()),
                new Password(req.getPassword()),
                null,
                new Age(req.getAge())
        );
    }

}
