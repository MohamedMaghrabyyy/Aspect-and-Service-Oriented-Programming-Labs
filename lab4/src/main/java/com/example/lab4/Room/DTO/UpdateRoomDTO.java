package com.example.lab4.Room.DTO;

import com.example.lab4.Room.DTO.UniqueRoomNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class UpdateRoomDTO {

    @UniqueRoomNumber
    private String number;

    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @Positive(message = "Price must be positive")
    private Double pricePerNight;

    private Boolean isAvailable;

    // Getters & Setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
