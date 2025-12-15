package org.radon.shopyorder.cart.application.port.in;

import org.radon.shopyorder.cart.domain.model.Cart;

public interface CreateCartUseCase {
    Cart createCart(Long userId);
}
