package org.radon.shopy.shared.aop.exceptionHandling.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends ExceptionModel {
    public AddressNotFoundException() {
        super("Address Not Found!", HttpStatus.NOT_FOUND);
    }
}
