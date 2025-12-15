package org.radon.shopyvendor.integration.cart.infrastructure.adapter;

import org.radon.grpc.AddItemToCartRequest;
import org.radon.grpc.RemoveItemFromCartRequest;
import org.radon.shopyvendor.integration.cart.application.port.out.CartClientRepository;
import org.radon.shopyvendor.integration.cart.infrastructure.repository.CartGrpcClient;
import org.radon.shopyvendor.integration.cart.model.Cart;
import org.radon.shopyvendor.integration.cart.presentation.controller.mappers.CartGrpcMappers;
import org.springframework.stereotype.Repository;

@Repository
public class CartClientRepositoryImpl implements CartClientRepository {

    private final CartGrpcClient cartGrpcClient;

    public CartClientRepositoryImpl(CartGrpcClient cartGrpcClient) {
        this.cartGrpcClient = cartGrpcClient;
    }

    @Override
    public Cart addItemToCart(AddItemToCartRequest request) {
        return CartGrpcMappers.toCart(cartGrpcClient.addItemToCart(request));
    }

    @Override
    public Cart removeItemToCart(RemoveItemFromCartRequest request) {
        return CartGrpcMappers.toCart(cartGrpcClient.removeItemFromCart(request));
    }
}
