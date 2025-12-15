package org.radon.shopyorder.order.presentation.dto.mapper;

import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderItem;
import org.radon.shopyorder.order.infrastructure.adapter.mapper.OrderMappers;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderEntity;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderItemEntity;
import org.radon.shopyorder.order.presentation.dto.OrderItemResponse;
import org.radon.shopyorder.order.presentation.dto.OrderResponse;
import org.radon.shopyorder.payment.domain.model.PaymentStatus;
import org.radon.shopyorder.payment.presentation.controller.PaymentController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

public class OrderMappersDto {

    public static final String baseUrl = "http://localhost:8090";

    public static String orderToPaymentLink(Order order){
        URI path = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(PaymentController.class).payment(
                        order.getOrderNumber(),order.getPaymentId(), PaymentStatus.SUCCESS
                )
        ).toUri();

        return baseUrl + path;
    }

//    public static String orderToPaymentLink(Order order) {
//        return "api/v1/payment/"+order.getOrderNumber()+"?payment_id="+order.getPaymentId()+"&status=SUCCESS";
//    }

    public static OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getOrderNumber(),
                order.getUserId(),
                order.getOrderStatus(),
                order.getTotalAmount(),
                order.getPaymentId(),
                order.getIdempotencyKey(),
                order.getItems().stream().map(OrderMappersDto::toOrderItemResponse).toList(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getCreatedAt().plusMinutes(15)
        );
    }

    public static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getProductId(),
                orderItem.getVendorId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
