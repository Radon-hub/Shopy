package org.radon.shopy.shared.aop.exceptionHandling.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordOldNotMatchException extends ExceptionModel {
    public PasswordOldNotMatchException() {
        super("Old Password Not Match!",HttpStatus.CONFLICT);
    }
}
