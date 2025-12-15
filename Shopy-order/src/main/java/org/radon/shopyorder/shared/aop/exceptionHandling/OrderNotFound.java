package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFound extends ExceptionModel{
    public OrderNotFound() {
        super("Order does not exists!", HttpStatus.NOT_FOUND);
    }
}
