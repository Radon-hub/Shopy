package org.radon.shopy.integration.cart.presentation.controller.mappers;

import org.radon.grpc.CartItemResponse;
import org.radon.grpc.CartResponse;
import org.radon.shopy.integration.cart.model.Cart;
import org.radon.shopy.integration.cart.model.CartItem;
import org.radon.shopy.integration.cart.model.CartStatus;
import org.radon.shopy.integration.cart.presentation.dto.CartDto;
import org.radon.shopy.integration.cart.presentation.dto.CartItemDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CartGrpcMappers {
    public static CartDto toCartDto(Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUserId(),
                cart.getCartStatus(),
                cart.getItems().stream().map(CartGrpcMappers::toCartItemDto).toList(),
                cart.getCreated_at(),
                cart.getUpdated_at()
        );
    }

    public static CartItemDto toCartItemDto(CartItem cartItem){
        return new CartItemDto(
                cartItem.getProductId(),
                cartItem.getVendorId(),
                cartItem.getQuantity(),
                cartItem.getPrice(),
                cartItem.getCreated_at(),
                cartItem.getUpdated_at()
        );
    }

    public static Cart toCart(CartResponse cartResponse){
        return new Cart(
                UUID.fromString(cartResponse.getId()),
                cartResponse.getUserId(),
                CartStatus.valueOf(cartResponse.getCartStatus()),
                cartResponse.getItemsList().stream().map(CartGrpcMappers::toCartItem).toList(),
                LocalDateTime.parse(cartResponse.getCreatedAt()),
                LocalDateTime.parse(cartResponse.getUpdatedAt())
        );
    }

    public static CartItem toCartItem(CartItemResponse cartItemResponse){
        return new CartItem(
                null,
                cartItemResponse.getProductId(),
                cartItemResponse.getVendorId(),
                cartItemResponse.getQuantity(),
                BigDecimal.valueOf(cartItemResponse.getPrice()),
                LocalDateTime.parse(cartItemResponse.getCreatedAt()),
                LocalDateTime.parse(cartItemResponse.getUpdatedAt())
        );
    }
}
