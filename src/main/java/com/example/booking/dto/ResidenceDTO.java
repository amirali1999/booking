package com.example.booking.dto;

import com.example.booking.enums.ResidenceFacilities;
import com.example.booking.enums.ResidenceType;
import com.example.booking.enums.StarType;
import com.example.booking.model.City;
import com.example.booking.model.Passenger;
import com.example.booking.model.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidenceDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Integer residenceNumber;
    private String name;
    private String residenceType;
    private long city;
    private Date departDate;
    private Date returnDate;
    private Integer numOfPassenger;
    private Collection<String> residenceFacilities;
    private String starType;
    private String logo;
    private List<Long> photos;
    private boolean cancel;
    private boolean deleted;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> passengers;
}
