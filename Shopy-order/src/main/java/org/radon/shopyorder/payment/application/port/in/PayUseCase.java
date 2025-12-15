package org.radon.shopyorder.payment.application.port.in;

import org.radon.shopyorder.payment.domain.model.Payment;
import org.radon.shopyorder.payment.domain.model.PaymentStatus;

public interface PayUseCase {
    Payment payment(String orderNumber,String paymentId, PaymentStatus paymentStatus);
}
