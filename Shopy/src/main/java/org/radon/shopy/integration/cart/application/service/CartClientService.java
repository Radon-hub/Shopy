package org.radon.shopy.integration.cart.application.service;

import org.radon.shopy.integration.cart.application.port.in.CheckForAddItemToCartUseCase;
import org.radon.shopy.integration.cart.application.port.in.CheckForRemoveItemFromCartUseCase;
import org.radon.shopy.integration.cart.application.port.in.GetCartUseCase;
import org.radon.shopy.integration.cart.application.port.out.CartClientRepository;
import org.radon.shopy.integration.cart.model.Cart;
import org.radon.shopy.integration.cart.model.CartItem;
import org.radon.shopy.integration.cart.presentation.dto.CheckForAddOrRemoveDto;
import org.springframework.stereotype.Service;

@Service
public class CartClientService implements GetCartUseCase, CheckForAddItemToCartUseCase, CheckForRemoveItemFromCartUseCase {

    private final CartClientRepository cartClientRepository;


    public CartClientService(CartClientRepository cartClientRepository) {
        this.cartClientRepository = cartClientRepository;
    }

    @Override
    public Cart getCart() {
        return cartClientRepository.getCart();
    }

    @Override
    public CartItem checkForAddItemToCart(CheckForAddOrRemoveDto request) {
        return cartClientRepository.checkForAddItemToCart(request);
    }

    @Override
    public CartItem checkForRemoveItemFromCart(CheckForAddOrRemoveDto request) {
        return cartClientRepository.checkForRemoveItemFromCart(request);
    }

}
