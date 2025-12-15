package org.radon.shopyorder.payment.application.service;

import org.radon.shopyorder.payment.application.port.in.PayUseCase;
import org.radon.shopyorder.payment.application.port.out.PaymentRepository;
import org.radon.shopyorder.payment.domain.model.Payment;
import org.radon.shopyorder.payment.domain.model.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PayUseCase {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment payment(String orderNumber,String paymentId, PaymentStatus paymentStatus) {
        return paymentRepository.payment(orderNumber,paymentId,paymentStatus);
    }
}
