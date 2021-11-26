package com.infpulse.studentspoll.model.formDto.userInfo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String email;

    private String name;

    private String surname;

    private Boolean isDeleted;

    private LocalDateTime creationTime;

    private LocalDateTime lastLoginTime;
}
