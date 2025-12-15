package org.radon.shopyorder.cart.infrastructure.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.radon.shopyorder.cart.domain.model.CartItem;
import org.radon.shopyorder.cart.domain.model.CartStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts_table")
@Getter
@Setter
@NoArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus = CartStatus.ACTIVE;

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItemEntity> cartItems = new ArrayList<>();

    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();

    public CartEntity(Long userId, CartStatus cartStatus, List<CartItemEntity> cartItems, LocalDateTime created_at, LocalDateTime updated_at) {
        this.userId = userId;
        this.cartStatus = cartStatus;
        this.cartItems = cartItems;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public CartEntity(Long userId, CartStatus cartStatus) {
        this.userId = userId;
        this.cartStatus = cartStatus;
    }
}
