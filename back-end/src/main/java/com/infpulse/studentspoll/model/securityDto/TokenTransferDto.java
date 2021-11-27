package com.infpulse.studentspoll.model.securityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenTransferDto {
    private final String jwtToken;
}
