package com.example.booking.service;

import com.example.booking.dto.TrainDTO;
import com.example.booking.enums.AirplaneClassType;
import com.example.booking.enums.AirplaneType;
import com.example.booking.enums.TrainFacilities;
import com.example.booking.enums.TrainType;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.TrainMapper;
import com.example.booking.model.City;
import com.example.booking.model.Photo;
import com.example.booking.model.Train;
import com.example.booking.repository.CityRepository;
import com.example.booking.repository.PhotoRepository;
import com.example.booking.repository.TrainRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class TrainService {
    private final TrainRepository trainRepository;
    private final CityRepository cityRepository;
    private final PhotoRepository photoRepository;
    private final TrainMapper trainMapper = Mappers.getMapper(TrainMapper.class);

    public TrainService(TrainRepository trainRepository, CityRepository cityRepository, PhotoRepository photoRepository) {
        this.trainRepository = trainRepository;
        this.cityRepository = cityRepository;
        this.photoRepository = photoRepository;
    }

    public Response getTrain(){
        List<Train> trains = trainRepository.findAll();
        return new Response(
                HttpStatus.OK,
                "Get train successfully",
                trainMapper.listOfObjectToDTO(trains),
                1
        );
    }
    public Response postTrain(TrainDTO trainDTO) throws BookingException.NotFoundException {
        Train train = trainMapper.DTOToObject(trainDTO,cityRepository);
        trainRepository.save(train);
        return new Response(
                HttpStatus.OK,
                "Post train successfully",
                trainMapper.ObjectToDTO(train),
                1
        );
    }
    public Response deleteTrain(Long id) throws BookingException.NotFoundException {
        Train train = trainRepository.findById(id).orElseThrow(()->new BookingException.NotFoundException("id"));
        trainRepository.delete(train);
        return new Response(HttpStatus.OK,"Delete train successfully",null,1);
    }
    public Response patchTrain(Long id, TrainDTO trainDTO) throws BookingException.NotFoundException {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new BookingException.NotFoundException("id"));
        if(trainDTO.getTrainNumber() != null){
            train.setTrainNumber(trainDTO.getTrainNumber());
        }
        if(trainDTO.getSourceCityId() != 0){
            City city = cityRepository.findById(trainDTO.getSourceCityId())
                    .orElseThrow(() -> new BookingException.NotFoundException("source city id"));
            train.setSourceCity(city);
        }
        if(trainDTO.getTargetCityId() != 0){
            City city = cityRepository.findById(trainDTO.getTargetCityId())
                    .orElseThrow(() -> new BookingException.NotFoundException("target city id"));
            train.setTargetCity(city);
        }
        if (trainDTO.getDepartDate() != null){
            train.setDepartDate(trainDTO.getDepartDate());
        }
        if(trainDTO.getReturnDate() != null){
            train.setReturnDate(trainDTO.getReturnDate());
        }
        if(trainDTO.getNumOfPassenger() != null){
            train.setNumOfPassenger(trainDTO.getNumOfPassenger());
        }
        if(trainDTO.getPrice() != 0){
            train.setPrice(trainDTO.getPrice());
        }
        if(trainDTO.getTrainType() != null){
            train.setTrainType(TrainType.valueOf(trainDTO.getTrainType()));
        }
        if(trainDTO.getLogo() != null){
            train.setLogo(trainDTO.getLogo());
        }
        train.setCancel(trainDTO.isCancel());
//        for(String facility:trainDTO.getTrainFacilities()){
//            boolean flag = true;
//            for(TrainFacilities trainFacility:train.getTrainFacilities()){
//                if(Objects.equals(trainFacility.toString(), facility)){
//                    flag = false;
//                    break;
//                }
//            }
//            if(flag){
//                TrainFacilities trainFacilities = TrainFacilities.valueOf(facility);
//                train.getTrainFacilities().add(trainFacilities);
//            }
//        }

//        for(Long photoId:trainDTO.getPhotosId()){
//            boolean flag = false;
//            for(Photo photo:train.getPhotos()){
//                if(photo.getId() == photoId){
//                    flag = true;
//                    break;
//                }
//            }
//            if (!flag){
//                Photo photo = photoRepository.findById(photoId)
//                        .orElseThrow(() -> new BookingException.NotFoundException("photo id"));
//                train.getPhotos().add(photo);
//            }
//        }

//        if(trainDTO.getPassengersId() != null){
//            train.setAirplaneClassType(AirplaneClassType.valueOf(trainDTO.getAirplaneClassType()));
//        }
        if(trainDTO.getLogo() != null){
            train.setLogo(trainDTO.getLogo());
        }
        trainRepository.save(train);
        return new Response(HttpStatus.OK,"Patch train successfully",null,1);
    }
}
