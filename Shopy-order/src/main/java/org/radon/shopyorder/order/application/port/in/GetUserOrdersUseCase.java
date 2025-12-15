package org.radon.shopyorder.order.application.port.in;

import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface GetUserOrdersUseCase {
    List<Order> getUserOrders(Long userID, List<OrderStatus> orderStatusList);
}
