package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectPaymentID extends ExceptionModel {
    public IncorrectPaymentID() {
        super("The payment id is incorrect!", HttpStatus.BAD_REQUEST);
    }
}
