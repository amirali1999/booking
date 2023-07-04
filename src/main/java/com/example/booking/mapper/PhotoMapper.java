package com.example.booking.mapper;

import com.example.booking.dto.PassengerDTO;
import com.example.booking.dto.PhotoDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.model.Passenger;
import com.example.booking.model.Photo;
import com.example.booking.repository.AirplaneRepository;
import com.example.booking.repository.ResidenceRepository;
import com.example.booking.repository.TrainRepository;
import com.example.booking.repository.UserRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface PhotoMapper {
    @BeforeMapping
    default void beforeMapping(PhotoDTO photoDTO,
                               @MappingTarget Photo photo,
                               TrainRepository trainRepository,
                               ResidenceRepository residenceRepository)
            throws BookingException.NotFoundException {
        if(photoDTO.getTrainId() != 0){
            photo.setTrain(trainRepository.findById(photoDTO.getTrainId())
                    .orElseThrow(()-> new BookingException.NotFoundException("id")));
        }
        else photo.setTrain(null);
        if(photoDTO.getResidenceId() != 0){
            photo.setResidence(residenceRepository.findById(photoDTO.getResidenceId())
                    .orElseThrow(() -> new BookingException.NotFoundException("id")));
        }
        else photo.setResidence(null);
    }
    @Mapping(source = "train.id",target = "trainId")
    @Mapping(source = "residence.id",target = "residenceId")
    PhotoDTO ObjectToDTO(Photo photo);
    @Mapping(target = "train",ignore = true)
    @Mapping(target = "residence",ignore = true)
    Photo DTOToObject(PhotoDTO photoDTO,
                          @Context TrainRepository trainRepository,
                          @Context ResidenceRepository residenceRepository);
    List<PhotoDTO> DTOList(List<Photo> photos);
}
