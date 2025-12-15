package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoCartFoundException extends ExceptionModel {
    public NoCartFoundException() {
        super("No cart found!", HttpStatus.NOT_FOUND);
    }
}
