package org.radon.shopyvendor.integration.cart.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long productId;
    private Long vendorId;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
