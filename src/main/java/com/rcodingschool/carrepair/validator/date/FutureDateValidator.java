package com.rcodingschool.carrepair.validator.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class FutureDateValidator implements ConstraintValidator<FutureDateConstraint, LocalDateTime> {

    @Override
    public void initialize(FutureDateConstraint futureDateConstraint) {
    }

    @Override
    public boolean isValid(LocalDateTime inputDate, ConstraintValidatorContext cxt) {
        return inputDate != null && inputDate.isAfter(LocalDateTime.now());
    }


}
