package org.radon.shopy.user.domain.model;

import java.io.Serializable;

public record PhoneNumber(String value) implements Serializable {
    public PhoneNumber{
        if(!value.matches("^[0-9]{11}$")){
            throw new IllegalArgumentException("Invalid phone number!");
        }
    }
}
