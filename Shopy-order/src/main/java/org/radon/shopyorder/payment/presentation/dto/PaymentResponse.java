package org.radon.shopyorder.payment.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.radon.shopyorder.payment.domain.model.PaymentStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private PaymentStatus paymentStatus;
    private String paymentId;
    private String orderNumber;
}
