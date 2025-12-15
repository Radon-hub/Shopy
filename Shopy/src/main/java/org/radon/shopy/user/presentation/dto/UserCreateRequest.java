package org.radon.shopy.user.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import org.radon.shopy.shared.aop.exceptionHandling.model.IllegalArgumentException;


@Setter
@Getter
public class UserCreateRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private Byte age;

    public UserCreateRequest(String fullName, String email, String phoneNumber, String password, Byte age) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.age = age;
    }

    public void validateInputs() throws IllegalArgumentException {

        if(fullName.length() < 5 || fullName.length() > 50){
            throw new IllegalArgumentException("Full name must be between 5 and 50 characters!");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email must be at correct format!");
        }
        if(phoneNumber.length() != 11){
            throw new IllegalArgumentException("Phone Number must have 11 digits!");
        }
        if(password == null || password.length() <= 4){
            throw new IllegalArgumentException("Password Required and must be more than 4 characters!");
        }
        if(age == null || age > 100 || age < 15){
            throw new IllegalArgumentException("Age Required (Between 15 to 99)");
        }

    }
}
