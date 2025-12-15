package org.radon.shopyvendor.shared.aop.exceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VendorRegisterArgumentException extends ExceptionModel {
    public VendorRegisterArgumentException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
