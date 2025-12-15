package org.radon.shopy.user.domain.model;

import java.io.Serializable;

public record Age(Byte value) implements Serializable {
    public Age{
        if(!(value > 15 && value < 99)){
            throw new IllegalArgumentException("Age should be between 15 and 99 characters!");
        }
    }
}
