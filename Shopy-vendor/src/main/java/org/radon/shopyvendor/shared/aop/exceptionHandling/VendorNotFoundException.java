package org.radon.shopyvendor.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VendorNotFoundException extends ExceptionModel {
    public VendorNotFoundException(String vendorID) {
        super("Vendor not found with this ID : " + vendorID , HttpStatus.NOT_FOUND);
    }
}
