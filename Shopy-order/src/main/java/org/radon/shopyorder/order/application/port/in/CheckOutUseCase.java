package org.radon.shopyorder.order.application.port.in;

import org.radon.shopyorder.order.domain.model.Order;

public interface CheckOutUseCase {
    Order checkout(String orderNumber);
}
