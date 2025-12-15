package org.radon.shopyorder.cart.application.port.in;

import org.radon.shopyorder.cart.domain.model.Cart;

import java.util.UUID;

public interface GetCartByIdUseCase {
    Cart getCartById(UUID id);
}
