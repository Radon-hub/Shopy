package org.radon.shopyorder.cart.domain.model;

import java.io.Serializable;

public enum CartStatus implements Serializable {
    ACTIVE,
    ABANDONED,
    CHECKED_OUT
}
