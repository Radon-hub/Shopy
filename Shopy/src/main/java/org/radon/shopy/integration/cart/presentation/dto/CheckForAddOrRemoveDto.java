package org.radon.shopy.integration.cart.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckForAddOrRemoveDto {
    private Long productId;
    private Long vendorId;
    private String cartId;
}
