package com.example.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResidenceFacilities {
    RESTAURANT("رستوران"),
    TWENTY_FOUR_SERVICE("خدمات شبانه روزی"),
    GYM("سالن بدنسازی"),
    POOL("استخر سر پوشیده"),
    SAUNA_AND_JACUZZI("سونا و جکوزی"),
    HAIRDRESSER("خدمات آرایشگاه و زیبایی"),
    KINDERGARTEN("محل بازی کودکان"),
    SHOP("فروشگاه"),
    PRAY_ROOM("نمازخانه"),
    COFFEE_SHOP("کافی شاپ"),
    CONFERENCE_ROOM("سالن کنفرانس");

    private final String name;
}
