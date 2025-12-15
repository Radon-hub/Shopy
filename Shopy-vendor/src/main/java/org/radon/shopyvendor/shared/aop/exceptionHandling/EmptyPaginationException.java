package org.radon.shopyvendor.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyPaginationException extends ExceptionModel {
    public EmptyPaginationException(String vendorId ,String pageNumber) {
        super("No product found this vendor ID : " + vendorId + " On this page : " + pageNumber,HttpStatus.NOT_FOUND);
    }
}
