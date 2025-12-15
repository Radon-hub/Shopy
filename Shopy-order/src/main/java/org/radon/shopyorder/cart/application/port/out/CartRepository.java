package org.radon.shopyorder.cart.application.port.out;

import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartItem;

import java.util.List;
import java.util.UUID;

public interface CartRepository {
    List<Cart> getAllCarts();
    Cart getCartById(UUID id);
    Cart getCartByUserId(Long userId);
    List<CartItem> getCartItemsByCartId(UUID cartId);
    Cart createCart(Long userId);
    Cart changeCartStatus(Cart cart);
    Cart deleteCart(UUID cartId,Long userId);
    Cart addItemToCart(UUID cartId,CartItem cartItem);
    Cart removeItemFromCart(UUID cartId,Long productId);
}
