package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class CartIsEmpty extends ExceptionModel {
    public CartIsEmpty() {
        super("This cart is empty!", HttpStatus.NO_CONTENT);
    }
}
