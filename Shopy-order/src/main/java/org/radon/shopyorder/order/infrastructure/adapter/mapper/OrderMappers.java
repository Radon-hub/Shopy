package org.radon.shopyorder.order.infrastructure.adapter.mapper;

import org.radon.shopyorder.cart.domain.model.CartItem;
import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderItem;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderEntity;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderItemEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderMappers {

    public static Order toOrder(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getOrderNumber(),
                orderEntity.getUserId(),
                orderEntity.getOrderStatus(),
                orderEntity.getTotalAmount(),
                orderEntity.getPaymentId(),
                orderEntity.getIdempotencyKey(),
                orderEntity.getItems().stream().map(OrderMappers::toOrderItem).toList(),
                orderEntity.getCreatedAt(),
                orderEntity.getUpdatedAt()
        );
    }

    public static OrderEntity toOrderEntity(Order order) {
        return new OrderEntity(
            order.getOrderNumber(),
                order.getUserId(),
                order.getOrderStatus(),
                order.getTotalAmount(),
                order.getPaymentId(),
                order.getIdempotencyKey(),
                null,
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

    public static OrderItemEntity toOrderItemEntity(OrderItem orderItem,OrderEntity order) {
        return new OrderItemEntity(
                orderItem.getProductId(),
                orderItem.getVendorId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                order
        );
    }

    public static OrderItem toOrderItem(OrderItemEntity orderItemEntity) {
        return new OrderItem(
                orderItemEntity.getId(),
                orderItemEntity.getProductId(),
                orderItemEntity.getVendorId(),
                orderItemEntity.getQuantity(),
                orderItemEntity.getPrice(),
                null
        );
    }

    public static OrderItemEntity toOrderItemEntityFromCartItem(CartItem cartItem,OrderEntity orderEntity) {
        return new OrderItemEntity(
                cartItem.getProductId(),
                cartItem.getVendorId(),
                cartItem.getQuantity(),
                cartItem.getPrice(),
                orderEntity
        );
    }

}
