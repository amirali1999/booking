package com.example.booking.mapper;

import com.example.booking.dto.AirplaneDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.model.Airplane;
import com.example.booking.model.Passenger;
import com.example.booking.repository.AirplaneRepository;
import com.example.booking.repository.CityRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = AirplaneRepository.class)
public interface AirplaneMapper {
    @Mapping(source = "passengers",target = "passengersId",qualifiedByName = "mapPassengersToId")
    @Mapping(source = "sourceCity.id",target = "sourceCityId")
    @Mapping(source = "targetCity.id",target = "targetCityId")
    AirplaneDTO ObjectToDTO(Airplane airplane);
    @Mapping(target = "sourceCity",
            expression = "java(cityRepository.findById(airplaneDTO.getSourceCityId())" +
                    ".orElseThrow(() -> new BookingException.NotFoundException(\"SourceCityId\")))")
    @Mapping(target = "targetCity",
            expression = "java(cityRepository.findById(airplaneDTO.getTargetCityId())" +
                    ".orElseThrow(() -> new BookingException.NotFoundException(\"TargetCityId\")))")
    Airplane DTOToObject(AirplaneDTO airplaneDTO, @Context CityRepository cityRepository)
            throws BookingException.NotFoundException;
    List<AirplaneDTO> DTOList(List<Airplane> airplanes);
    @Named("mapPassengersToId")
    static List<Long> mapPassengersToId(List<Passenger> passengers){
        if(passengers == null) return null;
        return passengers.stream().map(Passenger::getId).collect(Collectors.toList());
    }
}
