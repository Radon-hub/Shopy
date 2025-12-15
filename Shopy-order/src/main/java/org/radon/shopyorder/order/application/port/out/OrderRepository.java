package org.radon.shopyorder.order.application.port.out;

import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order checkout(String orderNumber);
    Order submitOrder(Order order);
    Order cancelOrder(Order order);
    Order getOrderByNumber(String orderNumber);
    List<Order> getUserOrders(Long userID,List<OrderStatus> orderStatusList);
}
