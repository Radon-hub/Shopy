package org.radon.shopy.integration.cart.application.port.out;

import org.radon.shopy.integration.cart.model.Cart;
import org.radon.shopy.integration.cart.model.CartItem;
import org.radon.shopy.integration.cart.presentation.dto.CheckForAddOrRemoveDto;

public interface CartClientRepository {
    Cart getCart();
    CartItem checkForAddItemToCart(CheckForAddOrRemoveDto request);
    CartItem checkForRemoveItemFromCart(CheckForAddOrRemoveDto request);
}
