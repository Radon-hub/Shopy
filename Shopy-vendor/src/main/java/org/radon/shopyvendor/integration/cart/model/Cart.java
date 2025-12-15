package org.radon.shopyvendor.integration.cart.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Cart implements Serializable {

    private UUID id;
    private Long userId;
    private CartStatus cartStatus;
    private List<CartItem> items;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Cart() {
    }

    public Cart(UUID id, Long userId, CartStatus cartStatus, List<CartItem> items, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.userId = userId;
        this.cartStatus = cartStatus;
        this.items = items;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
