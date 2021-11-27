package com.infpulse.studentspoll.model.securityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReloginResponseDto {
    private final String jwtToken;
    private final String email;
    private final Boolean isAuth;
}
