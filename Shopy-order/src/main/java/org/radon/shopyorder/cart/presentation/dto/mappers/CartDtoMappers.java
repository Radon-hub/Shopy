package org.radon.shopyorder.cart.presentation.dto.mappers;

import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartItem;
import org.radon.shopyorder.cart.domain.model.CartStatus;
import org.radon.shopyorder.cart.presentation.dto.AddItemToCartRequest;
import org.radon.shopyorder.cart.presentation.dto.CartResponse;
import org.radon.shopyorder.cart.presentation.dto.CartItemResponse;
import org.radon.shopyorder.cart.presentation.dto.ChangeCartStatusRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class CartDtoMappers {

    public static ChangeCartStatusRequest toChangeCartStatusRequest(Cart cart){
        return new ChangeCartStatusRequest(
                cart.getId(),
                cart.getCartStatus()
        );
    }

    public static Cart toCart(ChangeCartStatusRequest changeCartStatusRequest){
        return new Cart(
                changeCartStatusRequest.getCartId(),
                null,
                changeCartStatusRequest.getCartStatus(),
                null,
                null,
                LocalDateTime.now()
        );
    }

    public static CartResponse toCartDto(Cart cart){
        return new CartResponse(
                cart.getId(),
                cart.getUserId(),
                cart.getCartStatus(),
                cart.getItems().stream().map(CartDtoMappers::toCartItemDto).toList(),
                cart.getCreated_at(),
                cart.getUpdated_at()
        );
    }

    public static CartItemResponse toCartItemDto(CartItem cartItem){
        return new CartItemResponse(
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
             null,
                cartResponse.getUserId(), CartStatus.ACTIVE,null,null,null
        );
    }

    public static CartItem toCartItem(AddItemToCartRequest addItemToCartRequest){
        return new CartItem(
                null,
                addItemToCartRequest.getProductId(),
                addItemToCartRequest.getVendorId(),
                null,
                1,
                addItemToCartRequest.getPrice(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}
