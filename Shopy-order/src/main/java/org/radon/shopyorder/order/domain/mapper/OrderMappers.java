package org.radon.shopyorder.order.domain.mapper;

import org.radon.shopyorder.cart.domain.model.CartItem;
import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderItem;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderItemEntity;

import java.util.List;

public class OrderMappers {

    public static OrderItem toOrderItemFromCartItem(CartItem cartItem, Order order) {
        return new OrderItem(
                null,
                cartItem.getProductId(),
                cartItem.getVendorId(),
                cartItem.getQuantity(),
                cartItem.getPrice(),
                order
        );
    }
}
