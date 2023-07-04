package com.example.booking.service;

import com.example.booking.dto.CityDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.CityMapper;
import com.example.booking.model.City;
import com.example.booking.repository.CityRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Response getCity(){
        List<City> cities = cityRepository.findAll();
        return new Response(
                HttpStatus.OK,
                "Get cities sucessfully",
                cityMapper.DTOList(cities),
                1);
    }
    public Response postCity(CityDTO cityDTO) throws BookingException.DuplicateFieldException {
        if (!cityRepository.findByLabel(cityDTO.getLabel()).isPresent()){
            throw new BookingException.DuplicateFieldException("label");
        }
        if (cityRepository.findByValue(cityDTO.getValue()).isPresent()){
            throw new BookingException.DuplicateFieldException("value");
        }
        City city = cityMapper.DTOToObject(cityDTO);
        cityRepository.save(city);
        return new Response(
                HttpStatus.OK,
                "Post cities sucessfully",
                cityMapper.ObjectToDTO(city),
                1
        );
    }
    public Response deleteCity(Long id) throws BookingException.NotFoundException {
        City city = cityRepository.findById(id).orElseThrow(() -> new BookingException.NotFoundException("id"));
        cityRepository.delete(city);
        return new Response(HttpStatus.OK,"Delete cities sucessfully",null,1);
    }
    public Response patchCity(Long id, CityDTO cityDTO)
            throws BookingException.NotFoundException, BookingException.DuplicateFieldException {
        if (!cityRepository.findByLabel(cityDTO.getLabel()).isPresent()){
            throw new BookingException.DuplicateFieldException("label");
        }
        if (cityRepository.findByValue(cityDTO.getValue()).isPresent()){
            throw new BookingException.DuplicateFieldException("value");
        }
        if(!cityRepository.findById(id).isPresent()){
            throw new BookingException.NotFoundException("id");
        }
        City city = cityMapper.DTOToObject(cityDTO);
        city.setId(id);
        cityRepository.save(city);
        return new Response(
                HttpStatus.OK,
                "Patch cities sucessfully",
                cityMapper.ObjectToDTO(city),
                1
        );
    }
}
