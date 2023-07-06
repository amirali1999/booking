package com.example.booking.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ToString
public class LoginRequest {
    private String email;
    private String password;
}
