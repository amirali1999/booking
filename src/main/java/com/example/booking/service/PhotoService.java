package com.example.booking.service;

import com.example.booking.dto.PhotoDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.PhotoMapper;
import com.example.booking.mapper.ResidenceMapper;
import com.example.booking.model.Photo;
import com.example.booking.repository.PhotoRepository;
import com.example.booking.repository.ResidenceRepository;
import com.example.booking.repository.TrainRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final TrainRepository trainRepository;
    private final ResidenceRepository residenceRepository;
    private final PhotoMapper photoMapper = Mappers.getMapper(PhotoMapper.class);

    public PhotoService(PhotoRepository photoRepository, TrainRepository trainRepository, ResidenceRepository residenceRepository) {
        this.photoRepository = photoRepository;
        this.trainRepository = trainRepository;
        this.residenceRepository = residenceRepository;
    }

    public Response getPhoto(){
        List<Photo> photos = photoRepository.findAll();
        return new Response(HttpStatus.OK,"Get photo sucessfully",photos,1);
    }
    public Response postPhoto(PhotoDTO photoDTO) throws BookingException.NotFoundException {
        Photo photo = photoMapper.DTOToObject(photoDTO,trainRepository,residenceRepository);
        photoRepository.save(photo);
        return new Response(
                HttpStatus.OK,
                "Post photo sucessfully",
                photoMapper.ObjectToDTO(photo),
                1
        );
    }
    public Response deletePhoto(Long id) throws BookingException.NotFoundException {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new BookingException.NotFoundException("id"));
        photoRepository.delete(photo);
        return new Response(HttpStatus.OK,"Delete photo sucessfully",null,1);
    }
    public Response patchPhoto(Long id, PhotoDTO photoDTO){
        return new Response(HttpStatus.OK,"Patch photo sucessfully",null,1);
    }
}
