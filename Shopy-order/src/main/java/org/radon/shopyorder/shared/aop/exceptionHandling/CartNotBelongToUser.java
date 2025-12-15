package org.radon.shopyorder.shared.aop.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartNotBelongToUser extends ExceptionModel {
    public CartNotBelongToUser(String cartId,String userId) {
        super("This cart : (" + cartId + ") does not belong to this user : (" + userId + ")",HttpStatus.BAD_REQUEST);
    }
}
