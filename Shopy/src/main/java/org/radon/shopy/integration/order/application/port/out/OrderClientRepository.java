package org.radon.shopy.integration.order.application.port.out;

import org.radon.shopy.integration.order.presentation.dto.OrderResponseDto;
import org.radon.shopy.integration.order.presentation.dto.PaymentDto;

import java.util.List;

public interface OrderClientRepository {
    List<OrderResponseDto> getUserOrders();
    OrderResponseDto submitOrder(String CartId);
    String cancelOrder(String orderNumber);
    String checkoutOrder(String orderNumber);
    PaymentDto payment(PaymentDto paymentDto);
    OrderResponseDto getUserOrder(String orderNumber);
}
