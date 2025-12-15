package org.radon.shopyvendor.integration.cart.application.service;

import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.grpc.AddItemToCartRequest;
import org.radon.grpc.CartResponse;
import org.radon.grpc.GetCartRequest;
import org.radon.grpc.RemoveItemFromCartRequest;
import org.radon.shopyvendor.integration.cart.application.port.in.AddItemToCartUseCase;
import org.radon.shopyvendor.integration.cart.application.port.in.RemoveItemFromCartUseCase;
import org.radon.shopyvendor.integration.cart.application.port.out.CartClientRepository;
import org.radon.shopyvendor.integration.cart.model.Cart;
import org.radon.shopyvendor.integration.cart.presentation.dto.AddItemToCartRequestDto;
import org.radon.shopyvendor.integration.cart.presentation.dto.RemoveItemFromCartRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartClientService implements AddItemToCartUseCase, RemoveItemFromCartUseCase {

    private final CartClientRepository cartClientRepository;


    public CartClientService(CartClientRepository cartClientRepository) {
        this.cartClientRepository = cartClientRepository;
    }

    @Override
    public Cart addItemToCart(AddItemToCartRequestDto request) {
        return cartClientRepository.addItemToCart(
                AddItemToCartRequest.newBuilder()
                        .setCartId(request.getCartId())
                        .setProductId(request.getProductId())
                        .setVendorId(request.getVendorId())
                        .setPrice(request.getPrice())
                        .build());
    }

    @Override
    public Cart removeItemFromCart(RemoveItemFromCartRequestDto request) {
        return cartClientRepository.removeItemToCart(
                RemoveItemFromCartRequest.newBuilder()
                        .setCartId(request.getCartId())
                        .setProductId(request.getProductId())
                        .build()
        );
    }
}
