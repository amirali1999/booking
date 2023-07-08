package com.example.booking.service;

import com.example.booking.dto.ResidenceDTO;
import com.example.booking.enums.ResidenceType;
import com.example.booking.enums.StarType;
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
                "Get residence successfully",
                residenceMapper.DTOList(residences),
                1);
    }
    public Response getResidenceById(Long id) throws BookingException.NotFoundException {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new BookingException.NotFoundException("id"));
        return new Response(HttpStatus.OK,
                "Get residence successfully",
                residenceMapper.ObjectToDTO(residence),
                1
        );
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
    public Response patchResidence(Long id, ResidenceDTO residenceDTO) throws BookingException.NotFoundException {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new BookingException.NotFoundException("id"));
        if (residenceDTO.getResidenceNumber() != null){
            residence.setResidenceNumber(residenceDTO.getResidenceNumber());
        }
        if(residenceDTO.getName() != null){
            residence.setName(residenceDTO.getName());
        }
        if(residenceDTO.getResidenceType() !=  null){
            residence.setResidenceType(ResidenceType.valueOf(residenceDTO.getResidenceType()));
        }
        if(residenceDTO.getCityId() != 0){
            residence.setCity(cityRepository.findById(residenceDTO.getCityId())
                    .orElseThrow(()->new BookingException.NotFoundException("id")));
        }
        if(residenceDTO.getDepartDate() != null){
            residence.setDepartDate(residenceDTO.getDepartDate());
        }
        if(residenceDTO.getReturnDate() != null){
            residence.setReturnDate(residenceDTO.getReturnDate());
        }
        if(residenceDTO.getNumOfPassenger() != null){
            residence.setNumOfPassenger(residenceDTO.getNumOfPassenger());
        }
//        if(residenceDTO.getResidenceFacilities() != null){
//            residence.set
//        }
        if(residenceDTO.getStarType() != null){
            residence.setStarType(StarType.valueOf(residenceDTO.getStarType()));
        }
        if(residenceDTO.getLogo() != null){
            residence.setLogo(residenceDTO.getLogo());
        }
//        if(residenceDTO.getPhotosId() != null){
//            residence.set
//        }
        residence.setCancel(residenceDTO.isCancel());
        if(residenceDTO.getPrice() != 0){
            residence.setPrice(residenceDTO.getPrice());
        }
//        if(residenceDTO.getPassengersId() != null){
//
//        }
        residenceRepository.save(residence);
        return new Response(HttpStatus.OK,"Patch residence sucessfully",null,1);
    }
}
