package org.radon.shopy.integration.order.infrastructure.repository;


import org.radon.grpc.*;
import org.springframework.stereotype.Component;

@Component
public class OrderGrpcClient {

    private final OrdersGrpc.OrdersBlockingStub ordersBlockingStub;

    public OrderGrpcClient(OrdersGrpc.OrdersBlockingStub ordersBlockingStub) {
        this.ordersBlockingStub = ordersBlockingStub;
    }

    public OrderResponse submitOrder(SubmitOrderRequest submitOrderRequest) {
        return ordersBlockingStub.submitOrder(submitOrderRequest);
    }

    public UserCancelResponse cancelOrder(UserCancelRequest request) {
        return ordersBlockingStub.cancelOrder(request);
    }

    public UserOrdersResponse getUserOrders(UserOrdersRequest request) {
        return ordersBlockingStub.getUserOrders(request);
    }

    public OrderResponse getUserOrder(UserOrderRequest request) {
        return ordersBlockingStub.getUserOrder(request);
    }

    public UserCheckoutResponse checkoutOrder(UserOrderRequest request) {
        return ordersBlockingStub.checkoutOrder(request);
    }

    public PaymentResponse payment(PaymentRequest request) {
        return ordersBlockingStub.payment(request);
    }

}