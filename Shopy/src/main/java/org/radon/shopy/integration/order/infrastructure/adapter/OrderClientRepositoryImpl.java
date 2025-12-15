package org.radon.shopy.integration.order.infrastructure.adapter;

import lombok.val;
import org.radon.grpc.*;
import org.radon.shopy.integration.order.application.port.out.OrderClientRepository;
import org.radon.shopy.integration.order.infrastructure.repository.OrderGrpcClient;
import org.radon.shopy.integration.order.presentation.controller.mappers.OrdersMapper;
import org.radon.shopy.integration.order.presentation.dto.OrderResponseDto;
import org.radon.shopy.integration.order.presentation.dto.PaymentDto;
import org.radon.shopy.user.application.port.in.GetUserUseCase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderClientRepositoryImpl implements OrderClientRepository {

    private final OrderGrpcClient orderGrpcClient;
    private final GetUserUseCase getUserUseCase;

    public OrderClientRepositoryImpl(OrderGrpcClient orderGrpcClient, GetUserUseCase getUserUseCase) {
        this.orderGrpcClient = orderGrpcClient;
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public List<OrderResponseDto> getUserOrders() {
        val user = getUserUseCase.getUser();

        return orderGrpcClient.getUserOrders(
                UserOrdersRequest.newBuilder()
                        .setUserId(user.getId())
                        .build()
        ).getOrdersList().stream().map(OrdersMapper::toOrderResponseDto).toList();
    }

    @Override
    public OrderResponseDto submitOrder(String CartId) {

        val user = getUserUseCase.getUser();

        return OrdersMapper.toOrderResponseDto(orderGrpcClient.submitOrder(
                SubmitOrderRequest.newBuilder()
                        .setUserId(user.getId())
                        .setCartId(CartId)
                        .build()
        ));
    }

    @Override
    public String cancelOrder(String orderNumber) {
        val user = getUserUseCase.getUser();

        return orderGrpcClient.cancelOrder(
                UserCancelRequest.newBuilder()
                        .setOrderNumber(orderNumber)
                        .setUserId(user.getId())
                        .build()
        ).getOrderNumber();
    }

    @Override
    public String checkoutOrder(String orderNumber) {
        return orderGrpcClient.checkoutOrder(
                UserOrderRequest.newBuilder()
                        .setOrderNumber(orderNumber)
                        .build()
        ).getLink();
    }

    @Override
    public PaymentDto payment(PaymentDto paymentDto) {
        return OrdersMapper.toPaymentDto(
                orderGrpcClient.payment(
                        PaymentRequest.newBuilder()
                                .setOrderNumber(paymentDto.getOrderNumber())
                                .setPaymentId(paymentDto.getPaymentId())
                                .setPaymentStatus(paymentDto.getPaymentStatus())
                                .build()
                )
        );
    }

    @Override
    public OrderResponseDto getUserOrder(String orderNumber) {
        return OrdersMapper.toOrderResponseDto(orderGrpcClient.getUserOrder(
                UserOrderRequest.newBuilder()
                        .setOrderNumber(orderNumber)
                        .build()
        ));
    }
}
