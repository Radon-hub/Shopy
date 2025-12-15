package org.radon.shopyorder.cart.application.port.in;

import org.radon.shopyorder.cart.domain.model.Cart;

import java.util.UUID;

public interface ChangeCartStatusUseCase {
    Cart changeCartStatus(Cart cart);
}
