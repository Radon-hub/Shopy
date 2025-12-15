package org.radon.shopyvendor.integration.cart.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveItemFromCartRequestDto {
    private String cartId;
    private Long productId;
}