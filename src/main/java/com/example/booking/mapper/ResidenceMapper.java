package com.example.booking.mapper;

import com.example.booking.dto.PassengerDTO;
import com.example.booking.dto.ResidenceDTO;
import com.example.booking.model.Passenger;
import com.example.booking.model.Photo;
import com.example.booking.model.Residence;
import com.example.booking.repository.AirplaneRepository;
import com.example.booking.repository.ResidenceRepository;
import com.example.booking.repository.TrainRepository;
import com.example.booking.repository.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

public interface ResidenceMapper {
    @Mapping(source = "passengers",target = "passengersId",qualifiedByName = "mapPassengersToId")
    @Mapping(source = "photos",target = "photosId",qualifiedByName = "mapPhotosToId")
    @Mapping(source = "city.id",target = "cityId")
    ResidenceDTO ObjectToDTO(Residence residence);
    @Mapping(target = "city",
            expression = "java(residenceRepository.findById(residenceDTO.getCityId())" +
                    ".orElseThrow(()->new NotFoundException(\"cityId\")))")
    Residence DTOToObject(ResidenceDTO residenceDTO);
    List<ResidenceDTO> DTOList(List<Residence> residences);
    @Named("mapPassengersToId")
    static List<Long> mapPassengersToId(List<Passenger> passengers){
        return passengers.stream().map(Passenger::getId).collect(Collectors.toList());
    }
    @Named("mapPhotosToId")
    static List<Long> mapPhotosToId(List<Photo> photos){
        return photos.stream().map(Photo::getId).collect(Collectors.toList());
    }

}
