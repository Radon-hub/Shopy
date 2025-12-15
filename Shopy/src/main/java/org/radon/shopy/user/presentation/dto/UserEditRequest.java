package org.radon.shopy.user.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserEditRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private Byte age;
}
