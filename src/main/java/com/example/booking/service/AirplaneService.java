package com.example.booking.service;

import com.example.booking.dto.AirplaneDTO;
import com.example.booking.enums.AirplaneClassType;
import com.example.booking.enums.AirplaneTicketType;
import com.example.booking.enums.AirplaneType;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.AirplaneMapper;
import com.example.booking.model.Airplane;
import com.example.booking.model.City;
import com.example.booking.model.Residence;
import com.example.booking.repository.AirplaneRepository;
import com.example.booking.repository.CityRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final CityRepository cityRepository;
    private final AirplaneMapper airplaneMapper = Mappers.getMapper(AirplaneMapper.class);

    public AirplaneService(AirplaneRepository airplaneRepository, CityRepository cityRepository) {
        this.airplaneRepository = airplaneRepository;
        this.cityRepository = cityRepository;
    }

    public Response getAirplane(){
        List<Airplane> airplanes = airplaneRepository.findAll();
        return new Response(
                HttpStatus.OK,
                "Get airplanes sucessfully",
                airplaneMapper.DTOList(airplanes),
                1);
    }
    public Response postAirplane(AirplaneDTO airplaneDTO) throws BookingException.NotFoundException {
        Airplane airplane = airplaneMapper.DTOToObject(airplaneDTO,cityRepository);
        airplaneRepository.save(airplane);
        return new Response(HttpStatus.OK,
                "Post airplanes sucessfully",
                airplaneMapper.ObjectToDTO(airplane),
                1
        );
    }
    public Response deleteAirplane(Long id) throws BookingException.NotFoundException {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new BookingException.NotFoundException("id"));
        airplaneRepository.delete(airplane);
        return new Response(HttpStatus.OK,"Delete airplanes sucessfully",airplane,1);
    }
    public Response patchAirplane(Long id,AirplaneDTO airplaneDTO) throws BookingException.NotFoundException {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new BookingException.NotFoundException("id"));
        if(airplaneDTO.getFlightNumber() != null){
            airplane.setFlightNumber(airplaneDTO.getFlightNumber());
        }
        if(airplaneDTO.getSourceCityId() != 0){
            City city = cityRepository.findById(airplaneDTO.getSourceCityId())
                    .orElseThrow(() -> new BookingException.NotFoundException("source city id"));
            airplane.setSourceCity(city);
        }
        if(airplaneDTO.getTargetCityId() != 0){
            City city = cityRepository.findById(airplaneDTO.getTargetCityId())
                    .orElseThrow(() -> new BookingException.NotFoundException("target city id"));
            airplane.setTargetCity(city);
        }
        if (airplaneDTO.getDepartDate() != null){
            airplane.setDepartDate(airplaneDTO.getDepartDate());
        }
        if(airplaneDTO.getReturnDate() != null){
            airplane.setReturnDate(airplaneDTO.getReturnDate());
        }
        if(airplaneDTO.getNumOfPassenger() != null){
            airplane.setNumOfPassenger(airplaneDTO.getNumOfPassenger());
        }
        if(airplaneDTO.getPrice() != 0){
            airplane.setPrice(airplaneDTO.getPrice());
        }
        if(airplaneDTO.getTerminalNumber() != null){
            airplane.setTerminalNumber(airplaneDTO.getTerminalNumber());
        }
        if(airplaneDTO.getAmountOfLoad() != null){
            airplane.setAmountOfLoad(airplaneDTO.getAmountOfLoad());
        }
        if(airplaneDTO.getAirplaneType() != null){
            airplane.setAirplaneType(AirplaneType.valueOf(airplaneDTO.getAirplaneType()));
        }
        if(airplaneDTO.getAirplaneClassType() != null){
            airplane.setAirplaneClassType(AirplaneClassType.valueOf(airplaneDTO.getAirplaneClassType()));
        }
        if(airplaneDTO.getAirplaneTicketType() != null){
            airplane.setAirplaneTicketType(AirplaneTicketType.valueOf(airplaneDTO.getAirplaneTicketType()));
        }
        if(airplaneDTO.getLogo() != null){
            airplane.setLogo(airplaneDTO.getLogo());
        }
        airplane.setCancel(airplaneDTO.isCancel());
        airplaneRepository.save(airplane);
        return new Response(HttpStatus.OK,
                "Patch airplanes sucessfully",
                airplaneMapper.ObjectToDTO(airplane),
                1
        );
    }
}
