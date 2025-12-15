package org.radon.shopy.integration.order.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String orderNumber;
    private String paymentId;
    private String paymentStatus;
}
