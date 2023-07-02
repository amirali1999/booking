package com.example.booking.dto;

import com.example.booking.enums.AirplaneClassType;
import com.example.booking.enums.AirplaneTicketType;
import com.example.booking.enums.AirplaneType;
import com.example.booking.model.City;
import com.example.booking.model.Passenger;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirplaneDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Integer flightNumber;
    private City sourceCity;
    private City targetCity;
    private Date departDate;
    private Date returnDate;
    private Integer numOfPassenger;
    private long price;
    private Integer terminalNumber;
    private Integer amountOfLoad;
    private String airplaneType;
    private String airplaneClassType;
    private String airplaneTicketType;
    private String logo;
    private boolean cancel;
    private boolean deleted;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> passengers;
}
