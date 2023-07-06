    package com.example.booking.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private List<String> roles;
    private String refreshToken;

    public JwtResponse(String token, Long id, String firstName, String lastName, String email, Long phoneNumber, List<String> roles, String refreshToken) {
        this.token = token;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.refreshToken = refreshToken;
    }

    public JwtResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
