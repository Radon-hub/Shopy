package org.radon.shopyorder.order.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@Getter
@Setter
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;


    public OrderItemEntity(Long productId, Long vendorId, int quantity, BigDecimal price, OrderEntity orderEntity) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.quantity = quantity;
        this.price = price;
        this.orderEntity = orderEntity;
    }
}