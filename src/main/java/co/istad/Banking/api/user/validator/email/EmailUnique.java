package co.istad.Banking.api.user.validator.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = EmailUniqueConstraintsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EmailUnique {

    String message() default "The email field already exist.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}