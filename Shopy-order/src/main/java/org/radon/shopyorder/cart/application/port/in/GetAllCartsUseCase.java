package org.radon.shopyorder.cart.application.port.in;

import org.radon.shopyorder.cart.domain.model.Cart;

import java.util.List;

public interface GetAllCartsUseCase {
    List<Cart> getAllCarts();
}
