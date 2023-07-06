package com.example.booking.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private Long phoneNumber;
    private String password;
}
