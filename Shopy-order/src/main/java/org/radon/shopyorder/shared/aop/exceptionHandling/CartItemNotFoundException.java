package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFoundException extends ExceptionModel {
    public CartItemNotFoundException(String cartId) {
        super("Product not found in cart with ID : " + cartId,HttpStatus.NOT_FOUND);
    }
}
