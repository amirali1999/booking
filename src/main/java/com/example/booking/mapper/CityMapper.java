package com.example.booking.mapper;

import com.example.booking.dto.AirplaneDTO;
import com.example.booking.dto.CityDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.model.Airplane;
import com.example.booking.model.City;
import com.example.booking.repository.CityRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CityMapper {
    CityDTO ObjectToDTO(City city);
    City DTOToObject(CityDTO cityDTO);
    List<CityDTO> DTOList(List<City> cities);
}
