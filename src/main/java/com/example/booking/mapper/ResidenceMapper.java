package com.example.booking.mapper;

import com.example.booking.dto.PassengerDTO;
import com.example.booking.dto.ResidenceDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.model.Passenger;
import com.example.booking.model.Photo;
import com.example.booking.model.Residence;
import com.example.booking.repository.*;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;
@Mapper
public interface ResidenceMapper {
    @Mapping(source = "passengers",target = "passengersId",qualifiedByName = "mapPassengersToId")
    @Mapping(source = "photos",target = "photosId",qualifiedByName = "mapPhotosToId")
    @Mapping(source = "city.id",target = "cityId")
    ResidenceDTO ObjectToDTO(Residence residence);
    @Mapping(target = "city",
            expression = "java(cityRepository.findById(residenceDTO.getCityId())" +
                    ".orElseThrow(()->new BookingException.NotFoundException(\"cityId\")))")
    Residence DTOToObject(ResidenceDTO residenceDTO,@Context CityRepository cityRepository)
            throws BookingException.NotFoundException;
    List<ResidenceDTO> DTOList(List<Residence> residences);
    @Named("mapPassengersToId")
    static List<Long> mapPassengersToId(List<Passenger> passengers){
        if(passengers == null) return null;
        return passengers.stream().map(Passenger::getId).collect(Collectors.toList());
    }
    @Named("mapPhotosToId")
    static List<Long> mapPhotosToId(List<Photo> photos){
        if(photos == null) return null;
        return photos.stream().map(Photo::getId).collect(Collectors.toList());
    }

}
