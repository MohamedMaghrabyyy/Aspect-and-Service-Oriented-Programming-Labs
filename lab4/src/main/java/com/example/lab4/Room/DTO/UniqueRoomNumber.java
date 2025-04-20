package com.example.lab4.Room.DTO;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueRoomNumberValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRoomNumber {
    String message() default "Room number already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

