package com.example.booking.controller;

import com.example.booking.dto.PhotoDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.model.Photo;
import com.example.booking.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/photos")
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
    @GetMapping
    public ResponseEntity<?> getPhoto(){
        return photoService.getPhoto().createResponseEntity();
    }
    @PostMapping
    public ResponseEntity<?> postPhoto(@RequestBody PhotoDTO photoDTO) throws BookingException.NotFoundException {
        return photoService.postPhoto(photoDTO).createResponseEntity();
    }
    @DeleteMapping("{photoId}")
    public ResponseEntity<?> deletePhoto(@PathVariable("photoId") Long id) throws BookingException.NotFoundException {
        return photoService.deletePhoto(id).createResponseEntity();
    }
    @PatchMapping("{photoId}")
    public ResponseEntity<?> patchPhoto(@PathVariable("photoId") Long id,
                                        @RequestBody PhotoDTO photoDTO) throws BookingException.NotFoundException {
        return photoService.patchPhoto(id,photoDTO).createResponseEntity();
    }
}
