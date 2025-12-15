package org.radon.shopyorder.cart.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartRequest {
    private Long productId;
    private Long vendorId;
    private BigDecimal price ;
}
