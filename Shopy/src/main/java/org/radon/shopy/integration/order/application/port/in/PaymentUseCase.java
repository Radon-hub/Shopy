package org.radon.shopy.integration.order.application.port.in;

import org.radon.shopy.integration.order.presentation.dto.PaymentDto;

public interface PaymentUseCase {
    PaymentDto payment(PaymentDto paymentDto);
}
