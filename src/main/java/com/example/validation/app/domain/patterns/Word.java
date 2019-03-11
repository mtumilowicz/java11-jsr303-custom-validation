package com.example.validation.app.domain.patterns;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by mtumilowicz on 2019-03-07.
 */
@Constraint(validatedBy = WordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Word {
    String message() default "is not a proper word!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
