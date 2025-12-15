package org.radon.shopy.integration.order.application.port.in;

import org.radon.grpc.UserOrderRequest;

public interface CheckoutOrderUseCase {
    String checkoutOrder(String orderNumber);
}
