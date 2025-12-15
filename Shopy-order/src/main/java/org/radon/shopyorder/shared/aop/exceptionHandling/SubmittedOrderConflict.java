package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SubmittedOrderConflict extends ExceptionModel {
    public SubmittedOrderConflict() {
        super("This order has already been submitted!", HttpStatus.CONFLICT);
    }
}
