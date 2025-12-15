package org.radon.shopyorder.cart.application.port.in;

import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartItem;

import java.util.UUID;

public interface AddItemToCartUseCase {
    Cart addItemToCart(UUID cartId, CartItem cartItem);
}
