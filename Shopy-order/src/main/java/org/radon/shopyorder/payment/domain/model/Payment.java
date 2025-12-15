package org.radon.shopyorder.payment.domain.model;

import org.radon.shopyorder.order.domain.model.Order;

public class Payment {
    private Order order;
    private PaymentStatus status;

    public Payment(Order order, PaymentStatus status) {
        this.order = order;
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
