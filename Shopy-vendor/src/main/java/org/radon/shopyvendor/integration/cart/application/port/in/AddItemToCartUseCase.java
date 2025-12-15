package org.radon.shopyvendor.integration.cart.application.port.in;


import org.radon.shopyvendor.integration.cart.model.Cart;
import org.radon.shopyvendor.integration.cart.presentation.dto.AddItemToCartRequestDto;

public interface AddItemToCartUseCase {
    Cart addItemToCart(AddItemToCartRequestDto request);
}
