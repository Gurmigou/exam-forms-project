package com.infpulse.studentspoll.validation.editUser.validators;

import com.infpulse.studentspoll.validation.editUser.annotations.BothBlankOrBothNotBlank;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BothBlankOrBothNotBlankValidator implements ConstraintValidator<BothBlankOrBothNotBlank, Object> {
    private String field;
    private String fieldCompare;

    @Override
    public void initialize(BothBlankOrBothNotBlank constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldCompare = constraintAnnotation.fieldCompare();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String fieldValue = (String) new BeanWrapperImpl(value).getPropertyValue(field);
        String fieldCompareValue = (String) new BeanWrapperImpl(value).getPropertyValue(fieldCompare);

        if (fieldValue == null && fieldCompareValue == null)
            return true;
        else if (fieldValue != null && fieldCompareValue != null)
            return !fieldValue.isEmpty() && !fieldCompareValue.isEmpty();
        else
            return false;
    }
}
