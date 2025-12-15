package org.radon.shopyorder.order.presentation.controller.mapper;

import lombok.val;
import org.radon.shopyorder.OrderItemResponse;
import org.radon.shopyorder.OrderResponse;
import org.radon.shopyorder.PaymentResponse;
import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderItem;
import org.radon.shopyorder.payment.domain.model.Payment;

import java.util.UUID;

public class OrderGrpcMappers {

    public static OrderResponse toOrderResponse(Order order) {
        val builder =  OrderResponse.newBuilder()
                .setId(order.getId().toString())
                .setOrderNumber(order.getOrderNumber())
                .setUserId(order.getUserId())
                .setOrderStatus(order.getOrderStatus().toString())
                .setTotalAmount(order.getTotalAmount().doubleValue())
                .setPaymentId(order.getPaymentId())
                .setIdempotencyKey(order.getIdempotencyKey())
                .setCreatedAt(order.getCreatedAt().toString())
                .setUpdatedAt(order.getUpdatedAt().toString());

        order.getItems().forEach((item) -> {
            builder.addItems(toOrderItemResponse(item));
        });

        return builder.build();
    }

    public static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return OrderItemResponse.newBuilder()
                .setId(orderItem.getId().toString())
                .setProductId(orderItem.getProductId())
                .setVendorId(orderItem.getVendorId())
                .setQuantity(orderItem.getQuantity())
                .setPrice(orderItem.getPrice().doubleValue())
                .build();
    }

    public static PaymentResponse toPaymentResponse(Payment payment) {
        return PaymentResponse.newBuilder()
                .setPaymentId(payment.getOrder().getPaymentId())
                .setOrderNumber(payment.getOrder().getOrderNumber())
                .setPaymentStatus(payment.getStatus().name())
                .build();
    }

}
