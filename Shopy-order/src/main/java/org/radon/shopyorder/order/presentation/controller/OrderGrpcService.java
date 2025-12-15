package org.radon.shopyorder.order.presentation.controller;

import io.grpc.stub.StreamObserver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopyorder.*;
import org.radon.shopyorder.order.application.port.in.*;
import org.radon.shopyorder.order.presentation.controller.mapper.OrderGrpcMappers;
import org.radon.shopyorder.order.presentation.dto.mapper.OrderMappersDto;
import org.radon.shopyorder.payment.application.port.in.PayUseCase;
import org.radon.shopyorder.payment.domain.model.PaymentStatus;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.UUID;

@GrpcService
public class OrderGrpcService extends OrdersGrpc.OrdersImplBase {

    private final SubmitOrderUseCase submitOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final GetOrderByNumberUseCase getOrderByNumberUseCase;
    private final GetUserOrdersUseCase getUserOrdersUseCase;
    private final CheckOutUseCase checkOutUseCase;
    private final PayUseCase payUseCase;

    public OrderGrpcService(SubmitOrderUseCase submitOrderUseCase, CancelOrderUseCase cancelOrderUseCase, GetOrderByNumberUseCase getOrderByNumberUseCase, GetUserOrdersUseCase getUserOrdersUseCase, CheckOutUseCase checkOutUseCase, PayUseCase payUseCase) {
        this.submitOrderUseCase = submitOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.getOrderByNumberUseCase = getOrderByNumberUseCase;
        this.getUserOrdersUseCase = getUserOrdersUseCase;
        this.checkOutUseCase = checkOutUseCase;
        this.payUseCase = payUseCase;
    }

    @Transactional
    @Override
    public void submitOrder(SubmitOrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        val result = submitOrderUseCase.submitOrder(request.getUserId(), UUID.fromString(request.getCartId()));
        responseObserver.onNext(OrderGrpcMappers.toOrderResponse(result));
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void getUserOrders(UserOrdersRequest request, StreamObserver<UserOrdersResponse> responseObserver) {
        val result = getUserOrdersUseCase.getUserOrders(request.getUserId(),new ArrayList<>());
        val list = result.stream().map(OrderGrpcMappers::toOrderResponse).toList();
        responseObserver.onNext(UserOrdersResponse.newBuilder().addAllOrders(list).build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void getUserOrder(UserOrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        val result = getOrderByNumberUseCase.getOrderByNumberUseCase(request.getOrderNumber());
        responseObserver.onNext(OrderGrpcMappers.toOrderResponse(result));
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void checkoutOrder(UserOrderRequest request, StreamObserver<UserCheckoutResponse> responseObserver) {
        val result = checkOutUseCase.checkout(request.getOrderNumber());
        responseObserver.onNext(UserCheckoutResponse.newBuilder().setLink(OrderMappersDto.orderToPaymentLink(result)).build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void cancelOrder(UserCancelRequest request, StreamObserver<UserCancelResponse> responseObserver) {
        val result = cancelOrderUseCase.cancelOrder(request.getUserId(), request.getOrderNumber());
        responseObserver.onNext(UserCancelResponse.newBuilder().setOrderNumber(result.getOrderNumber()).build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void payment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        val result = payUseCase.payment(request.getOrderNumber(),request.getPaymentId(), PaymentStatus.valueOf(request.getPaymentStatus()));
        responseObserver.onNext(OrderGrpcMappers.toPaymentResponse(result));
        responseObserver.onCompleted();
    }

}
