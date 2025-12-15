package org.radon.shopy.user.domain.model;

import java.io.Serializable;

public record Password(String value) implements Serializable {
    public Password{
        if(value.length()<6){
            throw new IllegalArgumentException("Password length should be 6 characters!");
        }
    }
}
