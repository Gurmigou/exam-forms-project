package com.infpulse.studentspoll.model.formDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
