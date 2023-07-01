package com.example.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AirplaneTicketType {
    SYSTEM("سیستمی"),
    CHARTER("چارتری");

    private final String name;
}
