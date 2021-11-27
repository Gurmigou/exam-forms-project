package com.infpulse.studentspoll.model.formDto.userInfo;

import com.infpulse.studentspoll.validation.editUser.annotations.BlankOrSize;
import com.infpulse.studentspoll.validation.editUser.annotations.BothBlankOrBothNotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@BothBlankOrBothNotBlank(field = "oldPassword", fieldCompare = "newPassword")
public class EditUserDto {
    @BlankOrSize(min = 1, max = 255)
    private String newName;

    @BlankOrSize(min = 1, max = 255)
    private String newSurname;

    @BlankOrSize(min = 8, max = Integer.MAX_VALUE)
    private String oldPassword;

    @BlankOrSize(min = 8, max = Integer.MAX_VALUE)
    private String newPassword;
}
