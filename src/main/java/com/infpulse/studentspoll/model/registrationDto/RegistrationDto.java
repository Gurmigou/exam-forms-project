package com.infpulse.studentspoll.model.registrationDto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {
    @NotBlank
    @Size(min = 5, max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 255)
    private String password;

    @NotBlank
    @Size(min = 6, max = 255)
    private String confirmPassword;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
    @NotBlank
    @Size(min = 3, max = 255)
    private String surname;
}
