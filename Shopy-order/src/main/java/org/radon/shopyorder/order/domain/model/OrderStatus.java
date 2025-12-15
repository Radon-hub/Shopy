package org.radon.shopyorder.order.domain.model;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    CREATED,
    PENDING_PAYMENT,
    PROCESSING,
    EXPIRED,
    CONFIRMED,
    CANCELLED,
    FAILED
}
