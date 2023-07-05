package com.example.booking.service;

import com.example.booking.dto.ResidenceDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.ResidenceMapper;
import com.example.booking.model.Residence;
import com.example.booking.repository.CityRepository;
import com.example.booking.repository.ResidenceRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenceService {
    private final ResidenceRepository residenceRepository;
    private final CityRepository cityRepository;
    private final ResidenceMapper residenceMapper = Mappers.getMapper(ResidenceMapper.class);

    public ResidenceService(ResidenceRepository residenceRepository, CityRepository cityRepository) {
        this.residenceRepository = residenceRepository;
        this.cityRepository = cityRepository;
    }

    public Response getResidence(){
        List<Residence> residences = residenceRepository.findAll();
        return new Response(
                HttpStatus.OK,
                "Get residence sucessfully",
                residenceMapper.DTOList(residences),
                1);
    }
    public Response postResidence(ResidenceDTO residenceDTO) throws BookingException.NotFoundException {
        Residence residence = residenceMapper.DTOToObject(residenceDTO,cityRepository);
        residenceRepository.save(residence);
        return new Response(
                HttpStatus.OK,
                "Post residence sucessfully",
                residenceMapper.ObjectToDTO(residence),
                1
        );
    }
    public Response deleteResidence(Long id) throws BookingException.NotFoundException {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new BookingException.NotFoundException("id"));
        residenceRepository.delete(residence);
        return new Response(HttpStatus.OK,"Delete residence sucessfully",null,1);
    }
    public Response patchResidence(Long id, ResidenceDTO residenceDTO){
        return new Response(HttpStatus.OK,"Patch residence sucessfully",null,1);
    }
}
