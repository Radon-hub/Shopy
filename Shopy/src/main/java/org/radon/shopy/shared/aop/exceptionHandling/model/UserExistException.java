package org.radon.shopy.shared.aop.exceptionHandling.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistException extends ExceptionModel {
    public UserExistException() {
        super("User already exist!",HttpStatus.CONFLICT);
    }
}
