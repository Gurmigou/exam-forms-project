package com.infpulse.studentspoll.validation.editUser.annotations;

import com.infpulse.studentspoll.validation.editUser.validators.BothBlankOrBothNotBlankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BothBlankOrBothNotBlankValidator.class)
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BothBlankOrBothNotBlank {
    String message() default "One of the parameters isn't blank";

    //represents group of constraints
    Class<?>[] groups() default {};
    //represents additional information about annotation
    Class<? extends Payload>[] payload() default {};

    String field();
    String fieldCompare();
}
