package com.example.booking.mapper;

import com.example.booking.dto.AirplaneDTO;
import com.example.booking.dto.TrainDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.model.Airplane;
import com.example.booking.model.Passenger;
import com.example.booking.model.Photo;
import com.example.booking.model.Train;
import com.example.booking.repository.AirplaneRepository;
import com.example.booking.repository.CityRepository;
import com.example.booking.repository.ResidenceRepository;
import com.example.booking.repository.TrainRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

public interface TrainMapper {
    @Mapping(source = "passengers",target = "passengersId",qualifiedByName = "mapPassengersToId")
    @Mapping(source = "photos",target = "photosId",qualifiedByName = "mapPhotosToId")
    @Mapping(source = "sourceCity.id",target = "sourceCityId")
    @Mapping(source = "targetCity.id",target = "targetCityId")
    TrainMapper ObjectToDTO(Train train);
    @Mapping(target = "sourceCity",
            expression = "java(cityRepository.findById(airplaneDTO.getSourceCityId())" +
                    ".orElseThrow(() -> new BookingException.NotFoundException(\"SourceCityId\")))")
    @Mapping(target = "targetCity",
            expression = "java(cityRepository.findById(airplaneDTO.getTargetCityId())" +
                    ".orElseThrow(() -> new BookingException.NotFoundException(\"TargetCityId\")))")
    Train DTOToObject(TrainDTO trainDTO, @Context CityRepository cityRepository)
            throws BookingException.NotFoundException;
    List<TrainDTO> listOfObjectToDTO(List<Train> trains);
    @Named("mapPassengersToId")
    static List<Long> mapPassengersToId(List<Passenger> passengers){
        return passengers.stream().map(Passenger::getId).collect(Collectors.toList());
    }
    @Named("mapPhotosToId")
    static List<Long> mapPhotosToId(List<Photo> photos){
        return photos.stream().map(Photo::getId).collect(Collectors.toList());
    }
}
