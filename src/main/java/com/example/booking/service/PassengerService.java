package com.example.booking.service;

import com.example.booking.dto.PassengerDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.PassengerMapper;
import com.example.booking.model.Passenger;
import com.example.booking.repository.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final AirplaneRepository airplaneRepository;
    private final TrainRepository trainRepository;
    private final UserRepository userRepository;
    private final ResidenceRepository residenceRepository;
    private final PassengerMapper passengerMapper = Mappers.getMapper(PassengerMapper.class);

    public PassengerService(PassengerRepository passengerRepository, AirplaneRepository airplaneRepository, TrainRepository trainRepository, UserRepository userRepository, ResidenceRepository residenceRepository) {
        this.passengerRepository = passengerRepository;
        this.airplaneRepository = airplaneRepository;
        this.trainRepository = trainRepository;
        this.userRepository = userRepository;
        this.residenceRepository = residenceRepository;
    }

    public Response getPassenger(){
        List<Passenger> passengers = passengerRepository.findAll();
        return new Response(HttpStatus.OK,
                "Get Passenger sucessfully",
                passengerMapper.DTOList(passengers),
                1);
    }
    public Response postPassenger(PassengerDTO passengerDTO) throws BookingException.NotFoundException {
        Passenger passenger = passengerMapper.DTOToObject(
                passengerDTO,
                airplaneRepository,
                trainRepository,
                residenceRepository,
                userRepository);
        passengerRepository.save(passenger);
        return new Response(HttpStatus.OK,
                "Post Passenger sucessfully",
                passengerMapper.ObjectToDTO(passenger),
                1
        );
    }
    public Response deletePassenger(Long id) throws BookingException.NotFoundException {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new BookingException.NotFoundException("id"));
        passengerRepository.delete(passenger);
        return new Response(HttpStatus.OK,"Delete Passenger sucessfully",null,1);
    }
    public Response patchPassenger(Long id, PassengerDTO passengerDTO) throws BookingException.NotFoundException {
        if (!passengerRepository.findById(id).isPresent()){
            throw new BookingException.NotFoundException("id");
        }
        Passenger passenger = passengerMapper.DTOToObject(
                passengerDTO,
                airplaneRepository,
                trainRepository,
                residenceRepository,
                userRepository);
        passenger.setId(id);
        passengerRepository.save(passenger);
        return new Response(HttpStatus.OK,"Patch Passenger sucessfully",null,1);
    }
}
