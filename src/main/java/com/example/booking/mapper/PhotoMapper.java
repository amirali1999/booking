package com.example.booking.mapper;

import com.example.booking.dto.PassengerDTO;
import com.example.booking.dto.PhotoDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.model.Passenger;
import com.example.booking.model.Photo;
import com.example.booking.model.Train;
import com.example.booking.repository.*;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel="spring")
public interface PhotoMapper {
    @BeforeMapping
    default void validate(PhotoDTO photoDTO,
                               @MappingTarget Photo.PhotoBuilder photo,
                               @Context TrainRepository trainRepository,
                               @Context ResidenceRepository residenceRepository) throws BookingException.NotFoundException {
        if(photoDTO.getTrainId() != 0){
            photo.train(trainRepository.findById(photoDTO.getTrainId())
                    .orElseThrow(()-> new BookingException.NotFoundException("id")));
        }
        else photo.train(null);
        if(photoDTO.getResidenceId() != 0){
            photo.residence(residenceRepository.findById(photoDTO.getResidenceId())
                    .orElseThrow(() -> new BookingException.NotFoundException("id")));
        }
        else photo.residence(null);
    }
    @Mapping(source = "train.id",target = "trainId")
    @Mapping(source = "residence.id",target = "residenceId")
    PhotoDTO ObjectToDTO(Photo photo);
    @Mapping(target = "train",ignore = true)
    @Mapping(target = "residence",ignore = true)
    Photo DTOToObject(PhotoDTO photoDTO,
                      @Context TrainRepository trainRepository,
                      @Context ResidenceRepository residenceRepository)throws BookingException.NotFoundException;
    List<PhotoDTO> DTOList(List<Photo> photos);

}
