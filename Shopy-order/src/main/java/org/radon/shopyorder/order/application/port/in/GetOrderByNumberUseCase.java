package org.radon.shopyorder.order.application.port.in;

import org.radon.shopyorder.order.domain.model.Order;

import java.util.UUID;

public interface GetOrderByNumberUseCase {
    Order getOrderByNumberUseCase(String orderNumber);
}
