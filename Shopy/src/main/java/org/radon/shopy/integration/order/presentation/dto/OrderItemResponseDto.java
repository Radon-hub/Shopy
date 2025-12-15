package org.radon.shopy.integration.order.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {
    private String id;
    private Long productId;
    private Long vendorId;
    private int quantity;
    private double price;
}
