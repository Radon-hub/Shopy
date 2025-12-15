package org.radon.shopy.shared.aop.exceptionHandling.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordCanNotBeSameException extends ExceptionModel {
    public PasswordCanNotBeSameException() {
        super("New password can not be same as old one!",HttpStatus.CONFLICT);
    }
}
