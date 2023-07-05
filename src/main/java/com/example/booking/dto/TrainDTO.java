package com.example.booking.dto;

import com.example.booking.enums.TrainFacilities;
import com.example.booking.enums.TrainType;
import com.example.booking.model.City;
import com.example.booking.model.Passenger;
import com.example.booking.model.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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
@Builder
public class TrainDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Integer trainNumber;
    private Long sourceCityId;
    private Long targetCityId;
    private Date departDate;
    private Date returnDate;
    private Integer numOfPassenger;
    private Collection<String> trainFacilities;
    private String trainType;
    private String logo;
    private List<Long> photosId;
    private boolean cancel;
    private long price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> passengersId;
}
