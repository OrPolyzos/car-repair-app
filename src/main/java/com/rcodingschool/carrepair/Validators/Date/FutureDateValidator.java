package com.rcodingschool.carrepair.Validators.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class FutureDateValidator implements ConstraintValidator<FutureDateConstraint, LocalDateTime> {

    @Override
    public void initialize(FutureDateConstraint futureDateConstraint) {
    }

    @Override
    public boolean isValid(LocalDateTime inputDate,
                           ConstraintValidatorContext cxt) {
        if (inputDate != null && inputDate.isAfter(LocalDateTime.now())) {
            return true;
        } else {
            return false;
        }
    }


}
