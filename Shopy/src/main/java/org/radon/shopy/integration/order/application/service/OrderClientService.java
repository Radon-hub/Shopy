package org.radon.shopy.integration.order.application.service;

import org.radon.grpc.OrderResponse;
import org.radon.shopy.integration.order.application.port.in.*;
import org.radon.shopy.integration.order.application.port.out.OrderClientRepository;
import org.radon.shopy.integration.order.presentation.dto.OrderResponseDto;
import org.radon.shopy.integration.order.presentation.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderClientService implements SubmitOrderUseCase, CancelOrderUseCase , GetUserOrderUseCase , GetUserOrdersUseCase, CheckoutOrderUseCase,PaymentUseCase {

    private final OrderClientRepository orderClientRepository;

    public OrderClientService(OrderClientRepository orderClientRepository) {
        this.orderClientRepository = orderClientRepository;
    }

    @Override
    public OrderResponseDto submitOrder(String cartId) {
        return orderClientRepository.submitOrder(cartId);
    }

    @Override
    public String cancelOrder(String orderNumber) {
        return orderClientRepository.cancelOrder(orderNumber);
    }

    @Override
    public OrderResponseDto getUserOrder(String orderNumber) {
        return orderClientRepository.getUserOrder(orderNumber);
    }

    @Override
    public List<OrderResponseDto> getUserOrder() {
        return orderClientRepository.getUserOrders();
    }

    @Override
    public String checkoutOrder(String orderNumber) {
        return orderClientRepository.checkoutOrder(orderNumber);
    }

    @Override
    public PaymentDto payment(PaymentDto paymentDto) {
        return orderClientRepository.payment(paymentDto);
    }
}
