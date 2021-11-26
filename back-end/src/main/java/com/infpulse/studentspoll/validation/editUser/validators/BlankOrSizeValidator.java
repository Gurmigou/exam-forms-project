package com.infpulse.studentspoll.validation.editUser.validators;

import com.infpulse.studentspoll.validation.editUser.annotations.BlankOrSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BlankOrSizeValidator implements ConstraintValidator<BlankOrSize, String> {
    private int min;
    private int max;

    @Override
    public void initialize(BlankOrSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext validatorContext) {
        return s == null || (s.length() >= min && s.length() <= max);
    }
}
