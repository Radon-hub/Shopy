package org.radon.shopyorder.payment.presentation.dto.mapper;

import org.radon.shopyorder.payment.domain.model.Payment;
import org.radon.shopyorder.payment.presentation.dto.PaymentResponse;

public class PaymentMappersDto {
    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getStatus(),
                payment.getOrder().getPaymentId(),
                payment.getOrder().getOrderNumber()
        );
    }
}
