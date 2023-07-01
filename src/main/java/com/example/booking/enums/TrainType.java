package com.example.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrainType {
    FOUR_SEATER_COMPARTMENT("کوپه ای ۴ تخته"),
    SIX_SEATER_COMPARTMENT("کوپه ای ۶ تخته");

    private final String name;

}
