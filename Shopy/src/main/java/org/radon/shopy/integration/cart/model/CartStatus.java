package org.radon.shopy.integration.cart.model;

import java.io.Serializable;

public enum CartStatus implements Serializable {
    ACTIVE,
    ABANDONED,
    CHECKED_OUT
}