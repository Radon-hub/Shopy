package org.radon.shopy.integration.order.application.port.in;

import org.radon.grpc.OrderResponse;
import org.radon.shopy.integration.order.presentation.dto.OrderResponseDto;

public interface SubmitOrderUseCase {
    OrderResponseDto submitOrder(String CartId);
}
