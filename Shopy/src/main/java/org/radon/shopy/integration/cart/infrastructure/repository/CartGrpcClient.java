package org.radon.shopy.integration.cart.infrastructure.repository;


import org.radon.grpc.*;
import org.radon.shopy.integration.cart.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartGrpcClient {

    private final CartsGrpc.CartsBlockingStub cartsBlockingStub;

    public CartGrpcClient(CartsGrpc.CartsBlockingStub cartsBlockingStub) {
        this.cartsBlockingStub = cartsBlockingStub;
    }
    public CartResponse getCart(GetCartRequest request){
        return cartsBlockingStub.getUserCart(request);
    }

}