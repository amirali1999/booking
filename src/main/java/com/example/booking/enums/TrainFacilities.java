package com.example.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrainFacilities {
    VIDEO_SYSTEM("سیستم تصویری"),
    SOUND_SYSTEM("سیستم صوتی"),
    POWER_OUTLET("پریز برق"),
    RESTAURANT("فروش غذا در رستوران"),
    AIR_CONDITIONING_SYSTEM("سیستم تهویه"),
    WC("سرویس بهداشتی");

    private final String name;
}
