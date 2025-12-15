package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartIsAbandoned extends ExceptionModel {
    public CartIsAbandoned(String cartId) {
        super("This cart has been banned : (" + cartId + ")", HttpStatus.BAD_REQUEST);
    }
}
