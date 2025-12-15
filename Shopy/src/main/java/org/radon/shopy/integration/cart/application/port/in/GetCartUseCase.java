package org.radon.shopy.integration.cart.application.port.in;

import org.radon.grpc.CartResponse;
import org.radon.grpc.GetCartRequest;
import org.radon.shopy.integration.cart.model.Cart;

public interface GetCartUseCase {
    Cart getCart();
}
