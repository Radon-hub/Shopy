package org.radon.shopyorder.cart.infrastructure.adapter.mappers;

import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartItem;
import org.radon.shopyorder.cart.infrastructure.repository.entity.CartEntity;
import org.radon.shopyorder.cart.infrastructure.repository.entity.CartItemEntity;

public class CartMapper {


    public static CartEntity fromCart(Cart cart) {
        return new CartEntity(
                cart.getUserId(),
                cart.getCartStatus(),
                cart.getItems().stream().map(CartMapper::fromCartItem).toList(),
                cart.getCreated_at(),
                cart.getUpdated_at()
        );
    }

    public static Cart fromCartEntity(CartEntity cartEntity) {
        return new Cart(
                cartEntity.getId(),
                cartEntity.getUserId(),
                cartEntity.getCartStatus(),
                cartEntity.getCartItems().stream().map(CartMapper::fromCartItemEntity).toList(),
                cartEntity.getCreated_at(),
                cartEntity.getUpdated_at()
        );
    }


    public static CartItemEntity fromCartItem(CartItem cartItem) {
        return new CartItemEntity(
                cartItem.getProductId(),
                cartItem.getVendorId(),
                null,
                cartItem.getQuantity(),
                cartItem.getPrice(),
                cartItem.getCreated_at(),
                cartItem.getUpdated_at()
        );
    }

    public static CartItemEntity fromCartItem(Cart cart,CartItem cartItem) {
        return new CartItemEntity(
                cartItem.getProductId(),
                cartItem.getVendorId(),
                CartMapper.fromCart(cart),
                cartItem.getQuantity(),
                cartItem.getPrice(),
                cartItem.getCreated_at(),
                cartItem.getUpdated_at()
        );
    }

    public static Cart fromCart(CartEntity cartEntity) {
        return new Cart(
                cartEntity.getId(),
                cartEntity.getUserId(),
                cartEntity.getCartStatus(),
                cartEntity.getCartItems().stream().map(CartMapper::fromCartItemEntity).toList(),
                cartEntity.getCreated_at(),
                cartEntity.getUpdated_at()
        );
    }

    public static CartItem fromCartItemEntity(CartItemEntity cartItemEntity) {
        return new CartItem(
                cartItemEntity.getId(),
                cartItemEntity.getProductId(),
                cartItemEntity.getVendorId(),
                cartItemEntity.getCartEntity().getUserId(),
                cartItemEntity.getQuantity(),
                cartItemEntity.getPrice(),
                cartItemEntity.getCreated_at(),
                cartItemEntity.getUpdated_at()
        );
    }


}
