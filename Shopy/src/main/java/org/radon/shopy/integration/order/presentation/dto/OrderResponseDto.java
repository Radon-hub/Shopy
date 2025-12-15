package org.radon.shopy.integration.order.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private String id;
    private String orderNumber;
    private Long userId;
    private String orderStatus;
    private double totalAmount;
    private String paymentId;
    private String idempotencyKey;
    private List<OrderItemResponseDto> items;
    private String createdAt;
    private String updatedAt;
}
