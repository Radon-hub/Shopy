package org.radon.shopyorder.cart.presentation.controller.mapper;

import lombok.val;
import org.radon.shopyorder.AddItemToCartRequest;
import org.radon.shopyorder.CartItemResponse;
import org.radon.shopyorder.CartResponse;
import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartGRPCMappers {
    public static CartItemResponse toCartItemResponse(CartItem cartItem) {
        return CartItemResponse.newBuilder()
                .setProductId(cartItem.getProductId())
                .setQuantity(cartItem.getQuantity())
                .setVendorId(cartItem.getVendorId())
                .setPrice(cartItem.getPrice().doubleValue())
                .setCreatedAt(cartItem.getCreated_at().toString())
                .setUpdatedAt(cartItem.getUpdated_at().toString())
                .build();
    }
    public static CartItem toCartItem(AddItemToCartRequest request) {
        return new CartItem(
                null,
                request.getProductId(),
                request.getVendorId(),
                null,
                1,
                BigDecimal.valueOf(request.getPrice()),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static CartResponse toCartResponse(Cart cart) {
        CartResponse.Builder cartResponseBuilder = CartResponse.newBuilder()
                .setId(cart.getId().toString())
                .setUserId(cart.getUserId())
                .setCartStatus(cart.getCartStatus().toString())
                .setCreatedAt(cart.getCreated_at().toString())
                .setUpdatedAt(cart.getUpdated_at().toString());

        cart.getItems().forEach(item -> {
            cartResponseBuilder.addItems(toCartItemResponse(item));
        });

        return cartResponseBuilder.build();
    }

    public static CartItemResponse extractFromCart(AddItemToCartRequest request,Cart cart) {
        val item = cart.getItems().stream().filter(items -> items.getProductId().equals(request.getProductId())).findFirst();
        return item.map(CartGRPCMappers::toCartItemResponse).orElse(null);
    }

}
