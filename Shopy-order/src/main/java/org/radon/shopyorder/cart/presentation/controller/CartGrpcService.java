package org.radon.shopyorder.cart.presentation.controller;

import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopyorder.*;
import org.radon.shopyorder.cart.application.port.in.AddItemToCartUseCase;
import org.radon.shopyorder.cart.application.port.in.GetCartByUserIdUseCase;
import org.radon.shopyorder.cart.application.port.in.RemoveItemFromCartUseCase;
import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartItem;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

import static org.radon.shopyorder.cart.presentation.controller.mapper.CartGRPCMappers.*;

@GrpcService
public class CartGrpcService extends CartsGrpc.CartsImplBase {

    private final GetCartByUserIdUseCase getCartByUserIdUseCase;
    private final AddItemToCartUseCase addItemToCartUseCase;
    private final RemoveItemFromCartUseCase removeItemFromCartUseCase;

    public CartGrpcService(GetCartByUserIdUseCase getCartByUserIdUseCase, AddItemToCartUseCase addItemToCartUseCase, RemoveItemFromCartUseCase removeItemFromCartUseCase) {
        this.getCartByUserIdUseCase = getCartByUserIdUseCase;
        this.addItemToCartUseCase = addItemToCartUseCase;
        this.removeItemFromCartUseCase = removeItemFromCartUseCase;
    }

    @Transactional
    @Override
    public void addItemToCart(AddItemToCartRequest request, StreamObserver<CartResponse> responseObserver) {
        val item = addItemToCartUseCase.addItemToCart(UUID.fromString(request.getCartId()),toCartItem(request));
        responseObserver.onNext(toCartResponse(item));
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void removeItemFromCart(RemoveItemFromCartRequest request, StreamObserver<CartResponse> responseObserver) {
        val item = removeItemFromCartUseCase.removeItemFromCart(UUID.fromString(request.getCartId()),request.getProductId());
        responseObserver.onNext(toCartResponse(item));
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void getUserCart(GetCartRequest request, StreamObserver<CartResponse> responseObserver) {
        Cart userCart = getCartByUserIdUseCase.getCartByUserId(request.getUserId());
        responseObserver.onNext(toCartResponse(userCart));
        responseObserver.onCompleted();
    }






}
