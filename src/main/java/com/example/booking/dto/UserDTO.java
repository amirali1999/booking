package com.example.booking.dto;

import com.example.booking.model.Passenger;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private List<Long> passengersId;
}
