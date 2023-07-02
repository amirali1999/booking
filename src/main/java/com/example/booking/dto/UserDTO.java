package com.example.booking.dto;

import com.example.booking.model.Passenger;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String password;
    private String address;
    private Date birthday;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> passengers;
}
