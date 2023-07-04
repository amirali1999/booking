package com.example.booking.service;

import com.example.booking.dto.AirplaneDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.AirplaneMapper;
import com.example.booking.model.Airplane;
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
        if(!airplaneRepository.findById(id).isPresent()){
            throw new BookingException.NotFoundException("id");
        }
        Airplane airplane = airplaneMapper.DTOToObject(airplaneDTO,cityRepository);
        airplane.setId(id);
        airplaneRepository.save(airplane);
        return new Response(HttpStatus.OK,
                "Patch airplanes sucessfully",
                airplaneMapper.ObjectToDTO(airplane),
                1
        );
    }
}
