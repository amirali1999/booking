package com.example.booking.service;

import com.example.booking.dto.TrainDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.TrainMapper;
import com.example.booking.model.Train;
import com.example.booking.repository.CityRepository;
import com.example.booking.repository.TrainRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private final TrainRepository trainRepository;
    private final CityRepository cityRepository;
    private final TrainMapper trainMapper = Mappers.getMapper(TrainMapper.class);

    public TrainService(TrainRepository trainRepository, CityRepository cityRepository) {
        this.trainRepository = trainRepository;
        this.cityRepository = cityRepository;
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
    public Response patchTrain(Long id, TrainDTO trainDTO){
        return new Response(HttpStatus.OK,"Patch train successfully",null,1);
    }
}
