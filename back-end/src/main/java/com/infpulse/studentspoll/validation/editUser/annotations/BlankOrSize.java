package com.infpulse.studentspoll.validation.editUser.annotations;

import com.infpulse.studentspoll.validation.editUser.validators.BlankOrSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = BlankOrSizeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BlankOrSize {
    String message() default "A parameter isn't blank or parameter's size is out of boundaries";

    //represents group of constraints
    Class<?>[] groups() default {};

    //represents additional information about annotation
    Class<? extends Payload>[] payload() default {};

    int min();
    int max();
}
