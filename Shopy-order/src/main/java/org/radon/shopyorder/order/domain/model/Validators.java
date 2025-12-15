package org.radon.shopyorder.order.domain.model;

import org.radon.shopyorder.order.infrastructure.repository.entity.OrderItemEntity;

import java.util.List;

public class Validators {

    public static boolean orderItemsMatchCartItems(List<OrderItem> orderItems, List<OrderItemEntity> orderItemEntities){
        for(OrderItem orderItem : orderItems) {
            boolean match = orderItemEntities.stream().anyMatch(
                    orderItemEntity ->
                            orderItemEntity.getVendorId().equals(orderItem.getVendorId()) &&
                                    orderItemEntity.getProductId().equals(orderItem.getProductId()) &&
                                    orderItemEntity.getQuantity() == orderItem.getQuantity()
            );
            if(!match){return false;}
        }
        return true;
    }

}
