package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderNotReadyForPayment extends ExceptionModel {
    public OrderNotReadyForPayment() {
        super("This order is not ready for payment ... you need to checkout first!",HttpStatus.BAD_REQUEST);
    }
}
