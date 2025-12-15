package org.radon.shopyorder.order.domain.model;

import jakarta.persistence.*;
import org.radon.shopyorder.cart.domain.model.CartItem;
import org.radon.shopyorder.order.domain.mapper.OrderMappers;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderItemEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {

    private UUID id;
    private String orderNumber;
    private Long userId;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private String paymentId;
    private String idempotencyKey;
    private List<OrderItem> items = new ArrayList<>();
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Order(Long userID,List<CartItem> cartItems) {
        BigDecimal totalPrice = cartItems.stream().map(item -> BigDecimal.valueOf(item.getQuantity() * item.getPrice().doubleValue())).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.userId = userID;
        this.orderNumber = UUID.randomUUID().toString();
        this.orderStatus = OrderStatus.CREATED;
        this.totalAmount = totalPrice;
        this.paymentId = UUID.randomUUID().toString();
        this.idempotencyKey = UUID.randomUUID().toString();
        this.items = cartItems.stream().map(item -> OrderMappers.toOrderItemFromCartItem(item,this)).toList();
    }

    public Order(UUID id, String orderNumber, Long userId, OrderStatus orderStatus, BigDecimal totalAmount, String paymentId, String idempotencyKey, List<OrderItem> items, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.paymentId = paymentId;
        this.idempotencyKey = idempotencyKey;
        this.items = items;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
