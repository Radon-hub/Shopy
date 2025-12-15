package org.radon.shopyorder.payment.application.port.out;

import org.radon.shopyorder.payment.domain.model.Payment;
import org.radon.shopyorder.payment.domain.model.PaymentStatus;

public interface PaymentRepository {
    Payment payment(String orderNumber,String paymentId, PaymentStatus paymentStatus);
}
