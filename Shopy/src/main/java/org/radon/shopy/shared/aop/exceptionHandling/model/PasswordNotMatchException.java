package org.radon.shopy.shared.aop.exceptionHandling.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordNotMatchException extends ExceptionModel {
    public PasswordNotMatchException() {
        super("Passwords does not match!",HttpStatus.CONFLICT);
    }
}
