package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CartExistException extends ExceptionModel {
    public CartExistException(String userId) {
        super("Cart exist for user with ID : " + userId, HttpStatus.CONFLICT);
    }
}
