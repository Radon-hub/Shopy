package org.radon.shopyorder.cart.application.port.in;

import org.radon.shopyorder.cart.domain.model.Cart;

import java.util.UUID;

public interface DeleteCartUseCase {
    Cart deleteCart(UUID cartId, Long userId);
}
