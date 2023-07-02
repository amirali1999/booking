package com.example.booking.dto;

import com.example.booking.enums.TrainFacilities;
import com.example.booking.enums.TrainType;
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
public class TrainDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Integer trainNumber;
    private Long sourceCity;
    private Long targetCity;
    private Date departDate;
    private Date returnDate;
    private Integer numOfPassenger;
    private Collection<String> trainFacilities;
    private String trainType;
    private String logo;
    private List<Long> photos;
    private boolean cancel;
    private boolean deleted;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> passengers;
}
