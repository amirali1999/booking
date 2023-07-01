package com.example.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderType {
    MALE("مرد"),FEMALE("زن");
    private final String name;
}
