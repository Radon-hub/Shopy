package org.radon.shopyvendor.integration.cart.application.port.out;

import org.radon.grpc.AddItemToCartRequest;
import org.radon.grpc.CartResponse;
import org.radon.grpc.GetCartRequest;
import org.radon.grpc.RemoveItemFromCartRequest;
import org.radon.shopyvendor.integration.cart.model.Cart;

public interface CartClientRepository {
    Cart addItemToCart(AddItemToCartRequest request);
    Cart removeItemToCart(RemoveItemFromCartRequest request);
}
