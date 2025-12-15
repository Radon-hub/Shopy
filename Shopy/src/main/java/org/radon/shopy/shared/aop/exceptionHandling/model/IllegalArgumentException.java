package org.radon.shopy.shared.aop.exceptionHandling.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends ExceptionModel {
    public IllegalArgumentException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
