package com.rcodingschool.carrepair.Validators.Date;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FutureDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureDateConstraint {
    String message() default "Only future dates allowed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
