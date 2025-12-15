package org.radon.shopyorder.cart.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.radon.shopyorder.cart.domain.model.Cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "cart_items_table")
@Getter
@Setter
@NoArgsConstructor
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long productId;
    private Long vendorId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    private int quantity;
    private BigDecimal price;

    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();


    public CartItemEntity(Long productId, Long vendorId, CartEntity cartEntity, int quantity, BigDecimal price, LocalDateTime created_at, LocalDateTime updated_at) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.cartEntity = cartEntity;
        this.quantity = quantity;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
