package com.example.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StarType {
    ONE_STAR("یک ستاره"),
    TWO_STAR("دو ستاره"),
    THREE_STAR("سه ستاره"),
    FOUR_STAR("چهار ستاره"),
    FIVE_STAR("پنج ستاره");

    private final String name;
}
