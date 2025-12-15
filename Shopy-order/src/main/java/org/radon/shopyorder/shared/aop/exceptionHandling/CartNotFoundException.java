package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends ExceptionModel {
    public CartNotFoundException(String cartId) {
        super("Cart not found with this ID : " + cartId, HttpStatus.NOT_FOUND);
    }
}
