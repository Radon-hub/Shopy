package org.radon.shopyvendor.integration.cart.infrastructure.repository;


import jakarta.transaction.Transactional;
import org.radon.grpc.*;
import org.springframework.stereotype.Component;

@Component
public class CartGrpcClient {

    private final CartsGrpc.CartsBlockingStub cartsBlockingStub;

    public CartGrpcClient(CartsGrpc.CartsBlockingStub cartsBlockingStub) {
        this.cartsBlockingStub = cartsBlockingStub;
    }
    @Transactional
    public CartResponse addItemToCart(AddItemToCartRequest request){
        return cartsBlockingStub.addItemToCart(request);
    }
    @Transactional
    public CartResponse removeItemFromCart(RemoveItemFromCartRequest request){
        return cartsBlockingStub.removeItemFromCart(request);
    }
}