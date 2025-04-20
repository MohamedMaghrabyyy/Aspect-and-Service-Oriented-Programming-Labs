package com.example.lab4.Room.DTO;

import com.example.lab4.Room.RoomRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueRoomNumberValidator implements ConstraintValidator<UniqueRoomNumber, String> {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public boolean isValid(String number, ConstraintValidatorContext context) {
        if (number == null || number.trim().isEmpty()) return false;
        return !roomRepository.findByNumber(number).isPresent();
    }
}

