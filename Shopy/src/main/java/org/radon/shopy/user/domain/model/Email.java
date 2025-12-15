package org.radon.shopy.user.domain.model;

import java.io.Serializable;

public record Email(String value) implements Serializable {
    public Email{
        if(!(value.contains("@") && value.contains(".") && (value.contains("yahoo") || value.contains("gmail") || value.contains("hotmail")))) {
            throw new IllegalArgumentException("Email address is invalid!");
        }
    }
}
