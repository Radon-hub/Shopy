package org.radon.shopyorder.cart.application.port.in;

import org.radon.shopyorder.cart.domain.model.CartItem;

import java.util.List;
import java.util.UUID;

public interface GetCartItemsByCartIdUseCase {
    List<CartItem> getCartItemsByCartId(UUID cartId);
}
