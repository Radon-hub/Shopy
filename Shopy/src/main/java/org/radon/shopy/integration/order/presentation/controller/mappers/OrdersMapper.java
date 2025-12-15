package org.radon.shopy.integration.order.presentation.controller.mappers;

import org.radon.grpc.OrderItemResponse;
import org.radon.grpc.OrderResponse;
import org.radon.grpc.PaymentResponse;
import org.radon.shopy.integration.order.presentation.dto.OrderItemResponseDto;
import org.radon.shopy.integration.order.presentation.dto.OrderResponseDto;
import org.radon.shopy.integration.order.presentation.dto.PaymentDto;

public class OrdersMapper {
    public static OrderResponseDto toOrderResponseDto(OrderResponse orderResponse) {
        return new OrderResponseDto(
                orderResponse.getId(),
                orderResponse.getOrderNumber(),
                orderResponse.getUserId(),
                orderResponse.getOrderStatus(),
                orderResponse.getTotalAmount(),
                orderResponse.getPaymentId(),
                orderResponse.getIdempotencyKey(),
                orderResponse.getItemsList().stream().map(OrdersMapper::toOrderItemResponseDto).toList(),
                orderResponse.getCreatedAt(),
                orderResponse.getUpdatedAt()
        );
    }

    public static OrderItemResponseDto toOrderItemResponseDto(OrderItemResponse response) {
        return new OrderItemResponseDto(
                response.getId(),
                response.getProductId(),
                response.getVendorId(),
                response.getQuantity(),
                response.getPrice()
        );
    }

    public static PaymentDto toPaymentDto(PaymentResponse paymentResponse) {
        return new PaymentDto(
                paymentResponse.getOrderNumber(),
                paymentResponse.getPaymentId(),
                paymentResponse.getPaymentStatus()
        );
    }

}
