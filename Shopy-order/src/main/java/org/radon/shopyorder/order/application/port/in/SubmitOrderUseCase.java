package org.radon.shopyorder.order.application.port.in;

import org.radon.shopyorder.order.domain.model.Order;

import java.util.UUID;

public interface SubmitOrderUseCase {
    Order submitOrder(Long userID, UUID cartID);
}
