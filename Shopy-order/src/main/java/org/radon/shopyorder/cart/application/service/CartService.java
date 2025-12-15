package org.radon.shopyorder.cart.application.service;

import org.radon.shopyorder.cart.application.port.in.*;
import org.radon.shopyorder.cart.application.port.out.CartRepository;
import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartItem;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CartService implements AddItemToCartUseCase, ChangeCartStatusUseCase, CreateCartUseCase, DeleteCartUseCase,GetAllCartsUseCase,GetCartByIdUseCase,GetCartByUserIdUseCase,GetCartItemsByCartIdUseCase,RemoveItemFromCartUseCase {

    private final CartRepository cartRepository;
    private final CacheManager cacheManager;

    public CartService(CartRepository cartRepository, CacheManager cacheManager) {
        this.cartRepository = cartRepository;
        this.cacheManager = cacheManager;
    }

    @CachePut(value = "cart",key = "#result.userId")
    @Override
    public Cart addItemToCart(UUID cartId,CartItem cartItem) {
        return cartRepository.addItemToCart(cartId,cartItem);
    }

    @CachePut(value = "cart",key = "#cart.userId")
    @Override
    public Cart changeCartStatus(Cart cart) {
        return cartRepository.changeCartStatus(cart);
    }

    @CachePut(value = "cart",key = "#userId")
    @Override
    public Cart createCart(Long userId) {
        return cartRepository.createCart(userId);
    }

    @CacheEvict(value = "cart",key = "#userId")
    @Override
    public Cart deleteCart(UUID cartId,Long userId) {
        return cartRepository.deleteCart(cartId,userId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.getAllCarts();
    }

    @Override
    public Cart getCartById(UUID id) {
        return cartRepository.getCartById(id);
    }

    @Cacheable(value = "cart",key = "#userId")
    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.getCartByUserId(userId);
    }

    @Override
    public List<CartItem> getCartItemsByCartId(UUID cartId) {
        return cartRepository.getCartItemsByCartId(cartId);
    }

    @CachePut(value = "cart",key = "#result.userId")
    @Override
    public Cart removeItemFromCart(UUID cartId,Long productId) {
        Cart newCart = cartRepository.removeItemFromCart(cartId,productId);
        Objects.requireNonNull(cacheManager.getCache("cart")).put(newCart.getUserId(),newCart);
        return newCart;
    }

}
