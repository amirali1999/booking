package com.example.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResidenceType {
    HOTEL("هتل"),SUITE("سوییت");

    private final String name;
}
