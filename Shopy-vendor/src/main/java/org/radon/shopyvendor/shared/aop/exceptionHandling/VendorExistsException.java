package org.radon.shopyvendor.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VendorExistsException extends ExceptionModel{
    public VendorExistsException() {
        super("Vendor Already Exists!", HttpStatus.CONFLICT);
    }
}
