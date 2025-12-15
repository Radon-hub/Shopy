package org.radon.shopy.integration.order.application.port.in;

import org.radon.shopy.integration.order.presentation.dto.OrderResponseDto;

import java.util.List;

public interface GetUserOrdersUseCase {
    List<OrderResponseDto> getUserOrder();
}
