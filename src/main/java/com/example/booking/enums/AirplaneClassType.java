package com.example.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AirplaneClassType {
    BUSINESS("بیزینس"),
    ECONOMY("اکونومی");

    private final String name;
}
