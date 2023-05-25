package com.tolgaziftci.casestudy.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BoundedValueValidator implements ConstraintValidator<BoundedValue, Double> {
    double min;
    double max;
    @Override
    public void initialize(BoundedValue constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Double s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return true;
        return (s >= min) && (s <= max);
    }
}
