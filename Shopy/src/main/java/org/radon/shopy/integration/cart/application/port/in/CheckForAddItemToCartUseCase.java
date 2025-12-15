package org.radon.shopy.integration.cart.application.port.in;

import org.radon.shopy.integration.cart.model.CartItem;
import org.radon.shopy.integration.cart.presentation.dto.CheckForAddOrRemoveDto;

public interface CheckForAddItemToCartUseCase {
    CartItem checkForAddItemToCart(CheckForAddOrRemoveDto request);
}
