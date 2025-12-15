package org.radon.shopyvendor.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProductFoundException extends ExceptionModel {
    public NoProductFoundException(String vendorId) {
        super("No product found for this vendor ID : " + vendorId, HttpStatus.NOT_FOUND);
    }
}
