package org.radon.shopyorder.payment.presentation.controller;

import org.radon.shopyorder.payment.application.port.in.PayUseCase;
import org.radon.shopyorder.payment.domain.model.PaymentStatus;
import org.radon.shopyorder.payment.presentation.dto.PaymentResponse;
import org.radon.shopyorder.payment.presentation.dto.mapper.PaymentMappersDto;
import org.radon.shopyorder.shared.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final PayUseCase payUseCase;

    public PaymentController(PayUseCase payUseCase) {
        this.payUseCase = payUseCase;
    }

    @GetMapping("{orderNumber}")
    public ResponseEntity<Response<PaymentResponse>> payment(
            @PathVariable("orderNumber") String orderNumber,
            @RequestParam("payment_id") String paymentId,
            @RequestParam("status")PaymentStatus paymentStatus
    ) {
        return ResponseEntity.ok(new Response<>(PaymentMappersDto.from(payUseCase.payment(orderNumber, paymentId, paymentStatus))));
    }

}
