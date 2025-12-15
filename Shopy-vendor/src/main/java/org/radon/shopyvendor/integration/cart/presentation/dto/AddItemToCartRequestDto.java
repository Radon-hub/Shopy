package org.radon.shopyvendor.integration.cart.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartRequestDto {
    private String cartId;
    private Long vendorId;
    private Long productId;
    private double price;
}
