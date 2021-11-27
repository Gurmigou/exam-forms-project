package com.infpulse.studentspoll.model.securityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    @NotBlank
    @Email
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;

    @Size(min = 8)
    @NotBlank
    private String confirmPassword;

    @Size(min = 1, max = 255)
    @NotBlank
    private String name;

    @Size(min = 1, max = 255)
    @NotBlank
    private String surname;
}
