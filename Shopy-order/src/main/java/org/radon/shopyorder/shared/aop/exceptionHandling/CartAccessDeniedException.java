package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartAccessDeniedException extends ExceptionModel {
    public CartAccessDeniedException(String userID) {
        super("This Cart dose not belong to user with ID : " + userID, HttpStatus.BAD_REQUEST);
    }
}
