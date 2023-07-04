package com.example.booking.dto;

import com.example.booking.enums.GenderType;
import com.example.booking.model.Airplane;
import com.example.booking.model.Residence;
import com.example.booking.model.Train;
import com.example.booking.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String firstName;
    private String lastName;
    private long nationalCode;
    private long phoneNumber;
    private String gender;
    private long userId;
    private long airplaneId;
    private long trainId;
    private long residenceId;
}
