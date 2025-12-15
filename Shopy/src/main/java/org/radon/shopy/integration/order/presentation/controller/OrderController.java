package org.radon.shopy.integration.order.presentation.controller;

import org.radon.shopy.integration.order.application.port.in.*;
import org.radon.shopy.integration.order.presentation.dto.OrderResponseDto;
import org.radon.shopy.integration.order.presentation.dto.PaymentDto;
import org.radon.shopy.shared.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final SubmitOrderUseCase submitOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final GetUserOrderUseCase getUserOrderUseCase;
    private final GetUserOrdersUseCase getUserOrdersUseCase;
    private final CheckoutOrderUseCase checkoutOrderUseCase;
    private final PaymentUseCase paymentUseCase;

    public OrderController(SubmitOrderUseCase submitOrderUseCase, CancelOrderUseCase cancelOrderUseCase, GetUserOrderUseCase getUserOrderUseCase, GetUserOrdersUseCase getUserOrdersUseCase, CheckoutOrderUseCase checkoutOrderUseCase, PaymentUseCase paymentUseCase) {
        this.submitOrderUseCase = submitOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.getUserOrderUseCase = getUserOrderUseCase;
        this.getUserOrdersUseCase = getUserOrdersUseCase;
        this.checkoutOrderUseCase = checkoutOrderUseCase;
        this.paymentUseCase = paymentUseCase;
    }

    @PostMapping("payment")
    public ResponseEntity<Response<PaymentDto>> checkoutOrder(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(new Response<PaymentDto>(paymentUseCase.payment(paymentDto)),HttpStatus.OK);
    }

    @PostMapping("{orderNumber}/checkout")
    public ResponseEntity<Response<String>> checkoutOrder(@PathVariable(name = "orderNumber") String orderNumber) {
        return new ResponseEntity<>(new Response<String>(checkoutOrderUseCase.checkoutOrder(orderNumber)),HttpStatus.OK);
    }

    @PostMapping("{cartId}/submit")
    public ResponseEntity<Response<OrderResponseDto>> submitOrder(@PathVariable(name = "cartId") String cartId) {
        return new ResponseEntity<>(new Response<OrderResponseDto>(submitOrderUseCase.submitOrder(cartId)),HttpStatus.OK);
    }

    @PostMapping("{orderNumber}/cancel")
    public ResponseEntity<Response<String>> cancelOrder(@PathVariable(name = "orderNumber") String orderNumber) {
        return new ResponseEntity<>(new Response<String>(cancelOrderUseCase.cancelOrder(orderNumber)),HttpStatus.OK);
    }

    @GetMapping("{orderNumber}")
    public ResponseEntity<Response<OrderResponseDto>> getOrder(@PathVariable(name = "orderNumber") String orderNumber) {
        return new ResponseEntity<>(new Response<OrderResponseDto>(getUserOrderUseCase.getUserOrder(orderNumber)),HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Response<List<OrderResponseDto>>> getOrders() {
        return new ResponseEntity<>(new Response<List<OrderResponseDto>>(getUserOrdersUseCase.getUserOrder()),HttpStatus.OK);
    }

}
