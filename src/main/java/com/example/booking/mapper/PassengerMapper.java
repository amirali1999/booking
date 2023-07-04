package com.example.booking.mapper;

import com.example.booking.dto.CityDTO;
import com.example.booking.dto.PassengerDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.model.Airplane;
import com.example.booking.model.City;
import com.example.booking.model.Passenger;
import com.example.booking.repository.AirplaneRepository;
import com.example.booking.repository.ResidenceRepository;
import com.example.booking.repository.TrainRepository;
import com.example.booking.repository.UserRepository;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface PassengerMapper {
    @BeforeMapping
    default void beforeMapping(PassengerDTO passengerDTO,
                               @MappingTarget Passenger passenger,
                               AirplaneRepository airplaneRepository,
                               TrainRepository trainRepository,
                               ResidenceRepository residenceRepository)
            throws BookingException.NotFoundException {
        if(passengerDTO.getAirplaneId() != 0){
            passenger.setAirplane(airplaneRepository.findById(passengerDTO.getAirplaneId())
                    .orElseThrow(() -> new BookingException.NotFoundException("id")));
        }
        else passenger.setAirplane(null);
        if(passengerDTO.getTrainId() != 0){
            passenger.setTrain(trainRepository.findById(passengerDTO.getTrainId())
                    .orElseThrow(()-> new BookingException.NotFoundException("id")));
        }
        else passenger.setTrain(null);
        if(passengerDTO.getResidenceId() != 0){
            passenger.setResidence(residenceRepository.findById(passengerDTO.getResidenceId())
                    .orElseThrow(() -> new BookingException.NotFoundException("id")));
        }
        else passenger.setResidence(null);

    }
    @Mapping(source = "airplane.id",target = "airplaneId")
    @Mapping(source = "train.id",target = "trainId")
    @Mapping(source = "residence.id",target = "residenceId")
    PassengerDTO ObjectToDTO(Passenger passenger);
    @Mapping(target = "airplane",ignore = true)
    @Mapping(target = "train",ignore = true)
    @Mapping(target = "residence",ignore = true)
    @Mapping(target = "user",expression = "java(userRepository.findById(passengerDTO.getUserId())" +
            ".orElseThrow(() -> new BookingException.NotFoundException(\"userId\")))")
    Passenger DTOToObject(PassengerDTO passengerDTO,
                          @Context AirplaneRepository airplaneRepository,
                          @Context TrainRepository trainRepository,
                          @Context ResidenceRepository residenceRepository,
                          @Context UserRepository userRepository);
    List<PassengerDTO> DTOList(List<Passenger> passengers);
}
